package com.example.LibraryManagement.System.service;

import com.example.LibraryManagement.System.Enum.TransactionStatus;
import com.example.LibraryManagement.System.dto.responseDTO.IssueResponse;
import com.example.LibraryManagement.System.exception.BookNotAvailableException;
import com.example.LibraryManagement.System.model.Book;
import com.example.LibraryManagement.System.model.Student;
import com.example.LibraryManagement.System.model.Transaction;
import com.example.LibraryManagement.System.repository.BookRepository;
import com.example.LibraryManagement.System.repository.StudentRepository;
import com.example.LibraryManagement.System.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    final BookRepository bookRepository;
    final StudentRepository studentRepository;

    final TransactionRepository transactionRepository;
    @Autowired
    public TransactionService(BookRepository bookRepository, StudentRepository studentRepository, TransactionRepository transactionRepository) {
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
        this.transactionRepository = transactionRepository;
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

        // set data to issueResponse

        return IssueResponse.builder()
                .transactionNumber(saveTransaction.getTransactionNumber())
                .transactionTime(saveTransaction.getTransactionTime())
                .transactionStatus(saveTransaction.getTransactionStatus())
                .bookName(savebook.getTitle())
                .authorName(savebook.getAuthor().getName())
                .studentName(saveStudent.getName())
                .libraryCardNumber(saveStudent.getLibraryCard().getCardNo())
                .build();
    }
}
