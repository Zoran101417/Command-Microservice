package com.command_microservice.service;

import com.command_microservice.exception.GenericException;
import com.command_microservice.model.Faculty;
import com.command_microservice.model.dto.FacultyToEditDTO;

import java.util.List;
import java.util.Optional;

public interface FacultyService {
    Faculty save(Faculty faculty) throws GenericException;

    void delete(int facultyID) throws GenericException;

    Optional<Faculty> getFacultyById(int id);

    List<Faculty> getAllFaculties();

    Faculty update(Faculty faculty) throws GenericException;

}
