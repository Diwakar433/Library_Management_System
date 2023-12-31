package com.example.LibraryManagement.System.repository;

import com.example.LibraryManagement.System.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Author findByEmail(String email);

    Author findByName(String authorName);
}
