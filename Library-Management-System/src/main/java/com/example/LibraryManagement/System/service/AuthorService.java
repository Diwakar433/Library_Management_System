package com.example.LibraryManagement.System.service;

import com.example.LibraryManagement.System.model.Author;
import com.example.LibraryManagement.System.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    public String addAuthor(Author author) {
        Author saveAuthor = authorRepository.save(author);

        return "Author successfully added!";
    }
}
