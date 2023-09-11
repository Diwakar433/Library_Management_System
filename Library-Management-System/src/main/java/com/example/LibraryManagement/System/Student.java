package com.example.LibraryManagement.System;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Accessors
public class Student {

    @Id
    int regNo;
    String name;
    int age;
    @Enumerated(EnumType.STRING)
    Gender gender;
    String course;
    String email;

}
