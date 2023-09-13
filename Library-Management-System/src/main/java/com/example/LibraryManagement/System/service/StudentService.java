package com.example.LibraryManagement.System.service;

import com.example.LibraryManagement.System.Enum.CardStatus;
import com.example.LibraryManagement.System.Enum.Gender;
import com.example.LibraryManagement.System.model.LibraryCard;
import com.example.LibraryManagement.System.model.Student;
import com.example.LibraryManagement.System.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public String addStudent(Student student) {
        LibraryCard libraryCard = new LibraryCard();

        libraryCard.setCardNo(String.valueOf(UUID.randomUUID()));
        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setStudent(student);

        student.setLibraryCard(libraryCard);

        Student savedStudent = studentRepository.save(student); // save both student and libraryCard
        return "Student added Successfully";
    }

    public Student getStudent(int regNo) {

        Optional<Student> studentOptional = studentRepository.findById(regNo);
        if(studentOptional.isPresent()){
            return studentOptional.get();
        }
        return null;
    }

    public void deleteStudent(int regNo) {
        studentRepository.deleteById(regNo);
    }

    public Student updateAge(int regNo, int newAge) {
        Optional<Student> studentOptional = studentRepository.findById(regNo);

        if(studentOptional.isPresent()){
            Student student = studentOptional.get();
            student.setAge(newAge);

            studentRepository.save(student);
            return student;
        }


        return null;
    }

    public List<Student> allStudents() {
        return studentRepository.findAll();
    }

    public List<Student> allMaleStudents() {
        List<Student> allStudent = allStudents();
        allStudent.removeIf(student -> student.getGender() != Gender.MALE);
        if(allStudent == null)
            return null;
        return allStudent;
    }
}
