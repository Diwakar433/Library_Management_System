package com.example.LibraryManagement.System.dto.requestDTO;

import com.example.LibraryManagement.System.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentRequest {

    String name;

    int age;

    Gender gender;

    String course;

    String email;


}
