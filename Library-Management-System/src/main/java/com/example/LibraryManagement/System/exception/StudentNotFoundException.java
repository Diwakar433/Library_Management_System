package com.example.LibraryManagement.System.exception;

public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException(String Message) {
        super(Message);
    }
}
