package com.example.LibraryManagement.System;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired StudentRepository studentRepository;
    public Student addStudent(Student student) {
        Student savedStudent = studentRepository.save(student);
        return savedStudent;
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
        allStudent.removeIf(student -> student.getGender() != Gender.Male);
        if(allStudent == null)
            return null;
        return allStudent;
    }
}
