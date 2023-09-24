package com.example.LibraryManagement.System.exception;

public class BookNotAvailableException extends RuntimeException{

    public BookNotAvailableException(String message) {
        super(message);
    }
}
