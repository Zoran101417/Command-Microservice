package com.command_microservice.service;

import com.command_microservice.model.Grade;

import java.util.List;
import java.util.Optional;

public interface GradeService {
    List<Grade> getAllGrades();
    Optional<Grade> getGradeById(int id);
    Grade save(Grade grade);
    Grade update(Grade grade);
    void delete(int id);
}
