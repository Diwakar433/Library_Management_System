package com.example.LibraryManagement.System.controller;

import com.example.LibraryManagement.System.dto.responseDTO.AuthorResponse;
import com.example.LibraryManagement.System.model.Author;
import com.example.LibraryManagement.System.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;
    @PostMapping("/add")
    public ResponseEntity addAuthor(@RequestBody Author author) {
        String response = authorService.addAuthor(author);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    // update the email id of an author  -->> observer lastActivity column

    public ResponseEntity updateEmail(@RequestParam String email, @RequestParam String newEmail) {
        AuthorResponse authorResponse = authorService.updateEmail(email, newEmail);
        return new ResponseEntity<>(authorResponse, HttpStatus.FOUND);
    }
    // Give me the names of all the books written by a partiular author

    // give me the names of authors who have written more than 'x' number of books
}
