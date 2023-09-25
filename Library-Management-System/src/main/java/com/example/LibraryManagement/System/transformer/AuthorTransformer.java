package com.example.LibraryManagement.System.transformer;

import com.example.LibraryManagement.System.dto.responseDTO.AuthorResponse;
import com.example.LibraryManagement.System.model.Author;

public class AuthorTransformer {

    public static AuthorResponse AuthorToAuthorResponse(Author author) {

        return AuthorResponse.builder()
                .name(author.getName())
                .age(author.getAge())
                .email(author.getEmail())
                .lastActivity(author.getLastActivity())
                .build();
    }
}
