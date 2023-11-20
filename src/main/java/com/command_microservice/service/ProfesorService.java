package com.command_microservice.service;


import com.command_microservice.exception.GenericException;
import com.command_microservice.model.Profesor;

import java.util.List;
import java.util.Optional;

public interface ProfesorService {
    Profesor save(Profesor profesor) throws GenericException;

    void delete(int profesorID) throws GenericException;

    Optional<Profesor> getProfesorById(int id);

    List<Profesor> getAllProfesors();

    Profesor update(Profesor profesor) throws GenericException;

//    List<Profesor> getAllProfesorsByFacultyID(int facultyID) throws SQLException;
//
//    ProfessorsDTO getFacultyAndSubjects();
//
//    ProfessorsDTO getProfessorToEdit(int id);
}
