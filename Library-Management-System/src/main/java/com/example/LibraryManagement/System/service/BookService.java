package com.example.LibraryManagement.System.service;

import com.example.LibraryManagement.System.Enum.Genre;
import com.example.LibraryManagement.System.exception.AuthorNotFoundException;
import com.example.LibraryManagement.System.model.Author;
import com.example.LibraryManagement.System.model.Book;
import com.example.LibraryManagement.System.repository.AuthorRepository;
import com.example.LibraryManagement.System.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    public String addBook(Book book) {

        Optional<Author> saveAuthor = authorRepository.findById(book.getAuthor().getId());
        if(saveAuthor.isEmpty()) {
            throw new AuthorNotFoundException("Author Not Found");
        }
        Author author = saveAuthor.get();
        book.setAuthor(author);
        author.getBooks().add(book);
        Book saveBook = bookRepository.save(book);
        return "Book Added Successfully!";
    }

    public List<Book> getBookByGenreAndGreaterThanCost(Genre genre, double cost) {
        return bookRepository.getBookByGenreAndGreaterThanCost(genre, cost);
    }
}
