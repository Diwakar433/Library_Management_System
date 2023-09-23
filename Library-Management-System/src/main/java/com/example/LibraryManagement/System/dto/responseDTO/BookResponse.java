package com.example.LibraryManagement.System.dto.responseDTO;

import com.example.LibraryManagement.System.Enum.Genre;
import com.example.LibraryManagement.System.model.Author;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookResponse {

    String title;

    Genre genre;

    double cost;

    String authorName;

}
