package com.example.LibraryManagement.System.dto.responseDTO;

import com.example.LibraryManagement.System.Enum.CardStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.sql.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LibraryCardResponse {

    String cardNo;

    CardStatus cardStatus;

    Date issueDate;
}
