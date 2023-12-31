package com.example.LibraryManagement.System.dto.responseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class StudentResponse {

    String name;
    String email;

    String message;

    LibraryCardResponse libraryCardResponse;
}
