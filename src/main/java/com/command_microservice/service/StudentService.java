package com.command_microservice.service;

import com.command_microservice.exception.GenericException;
import com.command_microservice.model.Student;

import java.util.List;

public interface StudentService {
    Student save(Student student) throws GenericException;

    void delete(int studentID) throws GenericException;

    Student getStudentById(int id);

    List<Student> getAllStudents();

    Student update(Student student) throws GenericException;
}
