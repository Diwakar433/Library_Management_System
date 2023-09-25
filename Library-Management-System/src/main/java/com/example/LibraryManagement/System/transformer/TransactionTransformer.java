package com.example.LibraryManagement.System.transformer;

import com.example.LibraryManagement.System.dto.responseDTO.IssueResponse;
import com.example.LibraryManagement.System.model.Book;
import com.example.LibraryManagement.System.model.Student;
import com.example.LibraryManagement.System.model.Transaction;

public class TransactionTransformer {

    public static IssueResponse TransactionToIssueResponse(Transaction transaction) {

        Book book = transaction.getBook();
        Student student = transaction.getLibraryCard().getStudent();
        return IssueResponse.builder()
                .transactionNumber(transaction.getTransactionNumber())
                .transactionTime(transaction.getTransactionTime())
                .transactionStatus(transaction.getTransactionStatus())
                .bookName(book.getTitle())
                .authorName(book.getAuthor().getName())
                .studentName(student.getName())
                .libraryCardNumber(student.getLibraryCard().getCardNo())
                .build();
    }
}
