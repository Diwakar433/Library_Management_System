package com.example.LibraryManagement.System.transformer;

import com.example.LibraryManagement.System.dto.responseDTO.BookResponse;
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
}
