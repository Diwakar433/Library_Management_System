package com.example.LibraryManagement.System.exception;

public class AuthorNotFoundException extends RuntimeException{

    public AuthorNotFoundException(String Message) {
        super(Message);
    }
}
