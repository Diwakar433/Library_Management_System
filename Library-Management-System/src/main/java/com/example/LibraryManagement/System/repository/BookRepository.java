package com.example.LibraryManagement.System.repository;

import com.example.LibraryManagement.System.Enum.Genre;
import com.example.LibraryManagement.System.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "Select * from Book where genre = :genre and cost > :cost", nativeQuery = true)
    List<Book> getBookByGenreAndGreaterThanCost(Genre genre, double cost);
}
