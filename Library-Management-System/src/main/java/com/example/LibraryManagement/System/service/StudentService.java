package com.example.LibraryManagement.System.service;

import com.example.LibraryManagement.System.Enum.CardStatus;
import com.example.LibraryManagement.System.Enum.Gender;
import com.example.LibraryManagement.System.dto.requestDTO.StudentRequest;
import com.example.LibraryManagement.System.dto.responseDTO.LibraryCardResponse;
import com.example.LibraryManagement.System.dto.responseDTO.StudentResponse;
import com.example.LibraryManagement.System.model.LibraryCard;
import com.example.LibraryManagement.System.model.Student;
import com.example.LibraryManagement.System.repository.StudentRepository;
import com.example.LibraryManagement.System.transformer.LibraryCardTransformer;
import com.example.LibraryManagement.System.transformer.StudentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public StudentResponse addStudent(StudentRequest studentRequest) {
//        set the data from request to student, it prevents from user access and view original data.
        Student student = StudentTransformer.studentRequestToStudent(studentRequest);

//      set the library card data
        LibraryCard card = LibraryCardTransformer.prepareLibraryCard();
        card.setStudent(student);

        student.setLibraryCard(card); // set the libraryCard for student

        Student savedStudent = studentRepository.save(student); // save both student and libraryCard

//        for response to set data from saveStudent to studentResponse using transformer.
        return StudentTransformer.studentToStudentResponse(savedStudent);
    }

    public StudentResponse getStudent(int regNo) {

        Optional<Student> studentOptional = studentRepository.findById(regNo);
        if(studentOptional.isPresent()){
            Student student = studentOptional.get();
            return StudentTransformer.studentToStudentResponse(student);
        }
        return null;
    }

    public void deleteStudent(int regNo) {
        studentRepository.deleteById(regNo);
    }

    public StudentResponse updateAge(int regNo, int newAge) {
        Optional<Student> studentOptional = studentRepository.findById(regNo);

        if(studentOptional.isPresent()){
            Student student = studentOptional.get();
            student.setAge(newAge);

            studentRepository.save(student);

            return StudentTransformer.studentToStudentResponse(student);
        }


        return null;
    }

    public List<StudentResponse> allStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponse> studentResponseList = new ArrayList<>();
        for(Student student : students) {
            StudentResponse studentResponse = StudentTransformer.studentToStudentResponse(student);
            studentResponseList.add(studentResponse);
        }

        return studentResponseList;
    }

    public List<StudentResponse> allMaleStudents() {
        Gender gender = Gender.MALE;
        List<Student> studentList = studentRepository.findByGender(gender);

        List<StudentResponse> studentResponseList = new ArrayList<>();
        for(Student student : studentList) {
            StudentResponse studentResponse = StudentTransformer.studentToStudentResponse(student);
            studentResponseList.add(studentResponse);
        }
        return studentResponseList;
    }
}
