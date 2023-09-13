package com.example.LibraryManagement.System.model;

import com.example.LibraryManagement.System.Enum.CardStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String cardNo;

    @Enumerated(EnumType.STRING)
    CardStatus cardStatus;

    @CreationTimestamp
    Date issueDate;

    @OneToOne
    @JoinColumn
    Student student;

}
