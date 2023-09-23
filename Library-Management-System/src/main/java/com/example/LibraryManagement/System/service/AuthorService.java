package com.example.LibraryManagement.System.service;

import com.example.LibraryManagement.System.dto.responseDTO.AuthorResponse;
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

    public AuthorResponse updateEmail(String email, String newEmail) {
        Author author = authorRepository.findByEmail(email);
        author.setEmail(newEmail);

        AuthorResponse authorResponse = AuthorResponse.builder().name(author.getName()).
                age(author.getAge()).email(author.getEmail()).
                lastActivity(author.getLastActivity()).build();

        return authorResponse;
    }
}
