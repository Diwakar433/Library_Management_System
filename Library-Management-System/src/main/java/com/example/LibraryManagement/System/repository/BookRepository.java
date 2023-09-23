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
    List<Book> getBookByGenreAndGreaterThanCost(String genre, double cost);

    @Query(value = "select b from Book b where b.genre = :genre and b.cost > :cost")
    List<Book> getBooksByGenreAndCostGreaterThanHQL(Genre genre, double cost);

    List<Book> findByGenre(Genre genre);

    @Query(value = "Select * from Book where no_of_pages > :start and no_of_pages < :end", nativeQuery = true)
//    @Query(value = "select b form Book b where b.noOfPages > :start and b.noOfPages < :end")
    List<Book> allBookBYPageBetween(int start, int end);
}
