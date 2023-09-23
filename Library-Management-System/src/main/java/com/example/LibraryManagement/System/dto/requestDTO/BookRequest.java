package com.example.LibraryManagement.System.dto.requestDTO;

import com.example.LibraryManagement.System.Enum.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BookRequest {

    String title;

    int noOfPages;

    Genre genre;

    double cost;

}
