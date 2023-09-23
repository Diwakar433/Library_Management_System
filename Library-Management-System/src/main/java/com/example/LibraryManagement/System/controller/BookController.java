package com.example.LibraryManagement.System.controller;

import com.example.LibraryManagement.System.Enum.Genre;
import com.example.LibraryManagement.System.dto.responseDTO.BookResponse;
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
    public ResponseEntity getBookByGenreAndGreaterThanCost(@RequestParam String genre, @RequestParam double cost) {
        List<BookResponse> response = bookService.getBookByGenreAndGreaterThanCost(genre, cost);
        return new ResponseEntity(response, HttpStatus.FOUND);
    }

    @GetMapping("/get-by-genre-cost-hql")
    public List<BookResponse> getBooksByGenreAndCostGreaterThanHQL(@RequestParam("genre") Genre genre,
                                                                   @RequestParam("cost") double cost){
        return bookService.getBooksByGenreAndCostGreaterThanHQL(genre,cost);

    }

    // delete a book

    @PutMapping("/delete-by-id")
    public ResponseEntity deleteBook(@RequestParam int id) {
        String response = bookService.deleteBook(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // give me names of all the books of a particular genre

    @GetMapping("/get-by-genre")
    public ResponseEntity allBookByGenre(@RequestParam Genre genre) {

        List<BookResponse> bookResponse = bookService.allBookByGenre(genre);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }
    // give me all the books having number of pages between 'a' and 'b'

    @GetMapping("/get-by-page")
    public ResponseEntity allBookBYPageBetween(@RequestParam int start, @RequestParam int end) {
        List<BookResponse> bookResponse = bookService.allBookBYPageBetween(start, end);
        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }
    // give me the names of all the authors who write a particular genre
}
