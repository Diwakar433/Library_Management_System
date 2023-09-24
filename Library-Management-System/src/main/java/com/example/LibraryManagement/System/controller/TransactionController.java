package com.example.LibraryManagement.System.controller;

import com.example.LibraryManagement.System.dto.responseDTO.IssueResponse;
import com.example.LibraryManagement.System.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    final TransactionService transactionService;


    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/issue/book-id/{book-id}/student-id/{student-id}")
    public ResponseEntity issueBook(@PathVariable("book-id") int bookId, @PathVariable("student-id") int studentId) {
        try {
            IssueResponse issueResponse = transactionService.issueBook(bookId, studentId);
            return new ResponseEntity(issueResponse, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
