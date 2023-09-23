package com.example.LibraryManagement.System.service;

import com.example.LibraryManagement.System.Enum.Genre;
import com.example.LibraryManagement.System.dto.responseDTO.BookResponse;
import com.example.LibraryManagement.System.exception.AuthorNotFoundException;
import com.example.LibraryManagement.System.model.Author;
import com.example.LibraryManagement.System.model.Book;
import com.example.LibraryManagement.System.repository.AuthorRepository;
import com.example.LibraryManagement.System.repository.BookRepository;
import com.example.LibraryManagement.System.transformer.BookTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;

    public String deleteBook(int id) {
        bookRepository.deleteById(id);
        return "Book Data deleted Successfully";
    }

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

    public List<BookResponse> getBookByGenreAndGreaterThanCost(String genre, double cost) {
        List<Book> bookList =  bookRepository.getBookByGenreAndGreaterThanCost(genre, cost);

        List<BookResponse> bookResponseList = new ArrayList<>();

        for(Book book : bookList) {
            BookResponse bookResponse = BookTransformer.BookToBookResponse(book);
//            Add to the bookResponseList
            bookResponseList.add(bookResponse);
        }
        return bookResponseList;
    }

    public List<BookResponse> getBooksByGenreAndCostGreaterThanHQL(Genre genre, double cost) {
        List<Book> bookList =  bookRepository.getBooksByGenreAndCostGreaterThanHQL(genre, cost);

        List<BookResponse> bookResponseList = new ArrayList<>();

        for(Book book : bookList) {
            BookResponse bookResponse = BookTransformer.BookToBookResponse(book);
//            Add to the bookResponseList
            bookResponseList.add(bookResponse);
        }
        return bookResponseList;
    }

    public List<BookResponse> allBookByGenre(Genre genre) {
        List<Book> books = bookRepository.findByGenre(genre);

        List<BookResponse> bookResponseList = new ArrayList<>();

        for(Book book : books) {
            BookResponse bookResponse = BookTransformer.BookToBookResponse(book);
//            Add to the bookResponseList
            bookResponseList.add(bookResponse);
        }
        return bookResponseList;

    }

    public List<BookResponse> allBookBYPageBetween(int start, int end) {
        List<Book> bookList = bookRepository.allBookBYPageBetween(start, end);
        List<BookResponse> bookResponseList = new ArrayList<>();

        for(Book book : bookList) {
            BookResponse bookResponse = BookTransformer.BookToBookResponse(book);
//            Add to the bookResponseList
            bookResponseList.add(bookResponse);
        }
        return bookResponseList;
    }
}
