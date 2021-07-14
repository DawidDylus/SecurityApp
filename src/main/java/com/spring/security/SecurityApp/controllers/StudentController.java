package com.spring.security.SecurityApp.controllers;

import com.spring.security.SecurityApp.models.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/students")
public class StudentController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "James Cook"),
            new Student(2, "Maria Hay"),
            new Student(3, "Anna Smith")
    );


    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId){
return STUDENTS.stream()
        .filter(student -> studentId.equals(student.getStudentId()))
        .findFirst()
        .orElseThrow( ()-> new IllegalStateException("Student" + studentId + " does not exists"));

    }
}
