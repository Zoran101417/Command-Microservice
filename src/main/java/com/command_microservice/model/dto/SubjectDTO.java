package com.command_microservice.model.dto;

import com.command_microservice.model.Faculty;
import com.command_microservice.model.Subject;

import java.util.List;

public class SubjectDTO {
    private Subject subject;
    private List<Faculty> facultyList;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<Faculty> getFacultyList() {
        return facultyList;
    }

    public void setFacultyList(List<Faculty> facultyList) {
        this.facultyList = facultyList;
    }
}
