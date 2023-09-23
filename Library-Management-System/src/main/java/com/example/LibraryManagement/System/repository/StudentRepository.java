package com.example.LibraryManagement.System.repository;

import com.example.LibraryManagement.System.Enum.Gender;
import com.example.LibraryManagement.System.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByGender(Gender gender);
}
