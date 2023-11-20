package com.command_microservice.model.dto;

import com.command_microservice.model.Faculty;
import com.command_microservice.model.Profesor;
import com.command_microservice.model.Student;
import com.command_microservice.model.Subject;

import java.util.List;

public class FacultyDTO {
    private Faculty faculty;
    private List<Student> studentList;
    private List<Subject> subjectList;
    private List<Profesor> profesorList;

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public List<Profesor> getProfesorList() {
        return profesorList;
    }

    public void setProfesorList(List<Profesor> profesorList) {
        this.profesorList = profesorList;
    }

    @Override
    public String toString() {
        return "FacultyDTO{" +
                "faculty=" + faculty +
                ", studentList=" + studentList +
                ", subjectList=" + subjectList +
                '}';
    }
}
