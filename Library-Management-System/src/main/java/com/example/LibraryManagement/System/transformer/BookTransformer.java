package com.example.LibraryManagement.System.transformer;

import com.example.LibraryManagement.System.dto.responseDTO.BookResponse;
import com.example.LibraryManagement.System.dto.responseDTO.ResponseBook_AuthorAndGenre;
import com.example.LibraryManagement.System.model.Book;

public class BookTransformer {

    public static BookResponse BookToBookResponse(Book book) {

        return BookResponse.builder()
                .title(book.getTitle())
                .genre(book.getGenre())
                .cost(book.getCost())
                .authorName(book.getAuthor().getName())
                .build();
    }

    public static ResponseBook_AuthorAndGenre BookToResponseBookAuthorAndGenre(Book book) {
        return ResponseBook_AuthorAndGenre.builder()
                .title(book.getTitle())
                .authorName(book.getAuthor().getName())
                .genre(book.getGenre())
                .build();
    }
}
