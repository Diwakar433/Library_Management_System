package com.example.LibraryManagement.System.dto.responseDTO;


import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthorResponse {
    String name;

    int age;

    String email;

    String lastActivity;
}
