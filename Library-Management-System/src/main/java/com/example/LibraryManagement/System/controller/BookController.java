package com.example.LibraryManagement.System.controller;

import com.example.LibraryManagement.System.Enum.Genre;
import com.example.LibraryManagement.System.exception.AuthorNotFoundException;
import com.example.LibraryManagement.System.model.Book;
import com.example.LibraryManagement.System.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody Book book) {

        try {
            String response = bookService.addBook(book);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        catch (AuthorNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FOUND);
        }
    }

    @GetMapping("/get-genre-cost")
    public ResponseEntity getBookByGenreAndGreaterThanCost(@RequestParam Genre genre, @RequestParam double cost) {
        List<Book> response = bookService.getBookByGenreAndGreaterThanCost(genre, cost);
        return new ResponseEntity(response, HttpStatus.FOUND);
    }
}
