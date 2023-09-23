package com.example.LibraryManagement.System.transformer;

import com.example.LibraryManagement.System.dto.requestDTO.StudentRequest;
import com.example.LibraryManagement.System.dto.responseDTO.LibraryCardResponse;
import com.example.LibraryManagement.System.dto.responseDTO.StudentResponse;
import com.example.LibraryManagement.System.model.Student;

public class StudentTransformer {

    public static Student studentRequestToStudent(StudentRequest studentRequest) {

        return Student.builder()
                .name(studentRequest.getName())
                .age(studentRequest.getAge())
                .course(studentRequest.getCourse())
                .email(studentRequest.getEmail())
                .gender(studentRequest.getGender())
                .build();
    }
    public static StudentResponse studentToStudentResponse(Student student) {


        LibraryCardResponse cardResponse = LibraryCardResponse.builder()
                .cardNo(student.getLibraryCard().getCardNo())
                .cardStatus(student.getLibraryCard().getCardStatus())
                .issueDate(student.getLibraryCard().getIssueDate())
                .build();
        return StudentResponse.builder()
                .name(student.getName())
                .email(student.getEmail())
                .message("Data Found!")
                .libraryCardResponse(cardResponse)
                .build();
    }
}
