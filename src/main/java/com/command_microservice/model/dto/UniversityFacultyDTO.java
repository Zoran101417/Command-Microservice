package com.command_microservice.model.dto;

import com.command_microservice.model.Faculty;
import com.command_microservice.model.University;

import java.util.List;

public class UniversityFacultyDTO {
    private University university;
    private List<Faculty> facultyList;

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public List<Faculty> getFacultyList() {
        return facultyList;
    }

    public void setFacultyList(List<Faculty> facultyList) {
        this.facultyList = facultyList;
    }
}
