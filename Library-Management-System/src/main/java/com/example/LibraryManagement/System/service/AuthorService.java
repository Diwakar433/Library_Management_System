package com.example.LibraryManagement.System.service;

import com.example.LibraryManagement.System.dto.responseDTO.AuthorResponse;
import com.example.LibraryManagement.System.dto.responseDTO.ResponseBook_AuthorAndGenre;
import com.example.LibraryManagement.System.exception.AuthorNotFoundException;
import com.example.LibraryManagement.System.model.Author;
import com.example.LibraryManagement.System.model.Book;
import com.example.LibraryManagement.System.repository.AuthorRepository;
import com.example.LibraryManagement.System.repository.BookRepository;
import com.example.LibraryManagement.System.transformer.AuthorTransformer;
import com.example.LibraryManagement.System.transformer.BookTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
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

    public List<ResponseBook_AuthorAndGenre> allBooksByAuthor(String authorName) {
        Author author = authorRepository.findByName(authorName);
        if(author == null) {
            throw new AuthorNotFoundException("Author Name is wrong!");
        }
        List<ResponseBook_AuthorAndGenre> books = new ArrayList<>();

        for(Book book : author.getBooks()) {

           ResponseBook_AuthorAndGenre responseBookAuthorAndGenre = BookTransformer.BookToResponseBookAuthorAndGenre(book);
            books.add(responseBookAuthorAndGenre);
        }
        return books;
    }

    public List<AuthorResponse> authorNameWrittenMoreThanXBook(int count) {

        List<AuthorResponse> authors = new ArrayList<>();

        for(Author author : authorRepository.findAll()) {
            if(author.getBooks().size() >= count) {
                AuthorResponse authorResponse = AuthorTransformer.AuthorToAuthorResponse(author);
                authors.add(authorResponse);
            }
        }

        return authors;
    }
}
