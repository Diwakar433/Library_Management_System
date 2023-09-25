package com.example.LibraryManagement.System.dto.responseDTO;

import com.example.LibraryManagement.System.Enum.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseBook_AuthorAndGenre {

    String title;

    String authorName;

    Genre genre;
}
