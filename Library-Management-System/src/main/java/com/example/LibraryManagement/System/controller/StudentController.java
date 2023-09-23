package com.example.LibraryManagement.System.controller;

import com.example.LibraryManagement.System.Enum.CardStatus;
import com.example.LibraryManagement.System.dto.requestDTO.StudentRequest;
import com.example.LibraryManagement.System.dto.responseDTO.StudentResponse;
import com.example.LibraryManagement.System.model.LibraryCard;
import com.example.LibraryManagement.System.service.StudentService;
import com.example.LibraryManagement.System.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody StudentRequest studentRequest){

        StudentResponse response = studentService.addStudent(studentRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity getStudent(@RequestParam("id") int regNo){
        StudentResponse studentResponse = studentService.getStudent(regNo);
        if(studentResponse!=null){

            return new ResponseEntity(studentResponse,HttpStatus.FOUND);
        }
        return new ResponseEntity("Invalid id!",HttpStatus.BAD_REQUEST);
    }

    // delete a student --> regNo
    @PutMapping("/delete")
    public ResponseEntity deleteStudent(@RequestParam("id") int regNo) {
        studentService.deleteStudent(regNo);
        return new ResponseEntity("Deleted Successfully", HttpStatus.ACCEPTED);
    }


    // update the age of a student  ---> regNo, age

    @PutMapping("/updateAge")
    public ResponseEntity updateAge(@RequestParam("id") int regNo, @RequestParam("age") int newAge) {
        StudentResponse updatedStudent = studentService.updateAge(regNo, newAge);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    // get all the students in the db
    @GetMapping("/getAll")
    public ResponseEntity allStudents() {
        List<StudentResponse> allStudent = studentService.allStudents();
        return new ResponseEntity<>(allStudent, HttpStatus.FOUND);
    }

    // get list of all male students
    @GetMapping("/allMale")
    public ResponseEntity allMaleStudents(){
        List<StudentResponse> allStudent = studentService.allMaleStudents();
        return new ResponseEntity<>(allStudent, HttpStatus.FOUND);
    }
}
