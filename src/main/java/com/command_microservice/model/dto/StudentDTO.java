package com.command_microservice.model.dto;

import com.command_microservice.model.Student;
import com.command_microservice.model.Subject;

import java.util.List;

public class StudentDTO {
    private Student student;
    private List<Subject> subjectList;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }
}
