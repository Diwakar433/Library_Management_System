package com.example.LibraryManagement.System.service;

import com.example.LibraryManagement.System.Enum.TransactionStatus;
import com.example.LibraryManagement.System.dto.responseDTO.IssueResponse;
import com.example.LibraryManagement.System.exception.BookNotAvailableException;
import com.example.LibraryManagement.System.exception.TransactionNotFoundException;
import com.example.LibraryManagement.System.mailer.MailComposer;
import com.example.LibraryManagement.System.model.Book;
import com.example.LibraryManagement.System.model.LibraryCard;
import com.example.LibraryManagement.System.model.Student;
import com.example.LibraryManagement.System.model.Transaction;
import com.example.LibraryManagement.System.repository.BookRepository;
import com.example.LibraryManagement.System.repository.StudentRepository;
import com.example.LibraryManagement.System.repository.TransactionRepository;
import com.example.LibraryManagement.System.transformer.TransactionTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    final BookRepository bookRepository;
    final StudentRepository studentRepository;

    final TransactionRepository transactionRepository;

    final JavaMailSender javaMailSender;
    @Autowired
    public TransactionService(BookRepository bookRepository, StudentRepository studentRepository, TransactionRepository transactionRepository, JavaMailSender javaMailSender) {
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
        this.transactionRepository = transactionRepository;
        this.javaMailSender = javaMailSender;
    }

    public IssueResponse issueBook(int bookId, int studentId) {

        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        if(optionalStudent.isEmpty()) {
            throw new StringIndexOutOfBoundsException("Invalid Student Id!");
        }
        Student student = optionalStudent.get();

        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if(optionalBook.isEmpty()) {
            throw new BookNotAvailableException("Invalid Book Id!");
        }

        Book book = optionalBook.get();

        if(book.isIssue()) {
            throw new BookNotAvailableException("Book already issued!");
        }

        // set the transaction data
        Transaction transaction = Transaction.builder()
                .transactionNumber(String.valueOf(UUID.randomUUID()))
                .transactionStatus(TransactionStatus.SUCCESS)
                .book(book)
                .libraryCard(student.getLibraryCard())
                .build();

        // save the transaction because transaction will over-write below
        Transaction saveTransaction = transactionRepository.save(transaction);

        // set all api related data
        // update book
        book.setIssue(true);
        book.getTransactionList().add(saveTransaction);
        // update library card
        student.getLibraryCard().getTransactionList().add(saveTransaction);

        // save all data

        Book savebook =  bookRepository.save(book);
        Student saveStudent = studentRepository.save(student);


        // send mail
        SimpleMailMessage message = MailComposer.composeIssueBookEmail(savebook, saveStudent, saveTransaction);
        javaMailSender.send(message);
        // set data to issueResponse

        return TransactionTransformer.TransactionToIssueResponse(transaction);
    }

    public void releaseBook(int transactionId) {
        // check if transaction exists
        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
        if(transactionOptional.isEmpty()){
            throw new TransactionNotFoundException("Invalid transaction Id!");
        }
        // get transaction
        Transaction transaction = transactionOptional.get();
        // get corresponding book and library card
        Book book = transaction.getBook();
        LibraryCard libraryCard = transaction.getLibraryCard();
        // set book free
        book.setIssue(false);
        // remove transaction from its parent tables i.e. book and library card
        book.getTransactionList().remove(transaction);
        libraryCard.getTransactionList().remove(transaction);
        // remove transaction from transaction DB
        transactionRepository.delete(transaction);
        // save parent entities to update them
        Book savedBook = bookRepository.save(book);
        Student savedStudent = studentRepository.save(libraryCard.getStudent());

        // send email
        SimpleMailMessage message = MailComposer.sendReleaseBookEmail(savedStudent, savedBook, transaction);
        javaMailSender.send(message);
    }
}
