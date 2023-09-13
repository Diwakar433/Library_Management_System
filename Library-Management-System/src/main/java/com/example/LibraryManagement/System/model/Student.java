package com.example.LibraryManagement.System.model;

import com.example.LibraryManagement.System.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int regNo;

    String name;

    int age;

    @Enumerated(EnumType.STRING)
    Gender gender;

    String course;

    @Column(unique = true, nullable = false)
    String email;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    LibraryCard libraryCard;

}
