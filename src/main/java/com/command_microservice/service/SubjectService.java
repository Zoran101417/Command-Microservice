package com.command_microservice.service;

import com.command_microservice.exception.GenericException;
import com.command_microservice.model.Subject;
import com.command_microservice.model.Subject;
import com.command_microservice.model.dto.SubjectDTO;

import java.util.List;

public interface SubjectService {
    Subject save(Subject subject) throws GenericException;

    void delete(int subjectID) throws GenericException;

    Subject getSubjectById(int id);

    List<Subject> getAllSubjects();

    Subject update(Subject subject) throws GenericException;

    SubjectDTO getSubjectToEdit(int id);
}
