package com.command_microservice.service.impl;


import com.command_microservice.exception.GenericException;
import com.command_microservice.model.Profesor;
import com.command_microservice.repository.ProfessorRepository;
import com.command_microservice.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    ProfessorRepository professorRepository;

    private final KafkaTemplate<String, Object> multiTypeKafkaTemplate;

    public ProfesorServiceImpl(KafkaTemplate<String, Object> multiTypeKafkaTemplate) {
        this.multiTypeKafkaTemplate = multiTypeKafkaTemplate;
    }

    @Override
    @Transactional(rollbackFor = GenericException.class)
    public Profesor save(Profesor professor) throws GenericException {
        Profesor savedProfessor;
        try {
            savedProfessor = professorRepository.save(professor);
            this.multiTypeKafkaTemplate.send("saveProfesor", professor);
        } catch (Exception e) {
            throw new GenericException("An error occurred while saving the professor: " + e.getMessage(), e);
        }
        return savedProfessor;
    }

    @Override
    @Transactional(rollbackFor = GenericException.class)
    public Profesor update(Profesor professor) throws GenericException {
        Profesor updatedProfessor;
        try {
            updatedProfessor = professorRepository.save(professor);
            this.multiTypeKafkaTemplate.send("saveProfesor", professor);
        } catch (Exception e) {
            throw new GenericException("An error occurred while updateing the professor: " + e.getMessage(), e);
        }
        return updatedProfessor;
    }

    @Override
    @Transactional(rollbackFor = GenericException.class)
    public void delete(int professorID) throws GenericException {
        try {
            professorRepository.deleteById(professorID);
            this.multiTypeKafkaTemplate.send("deleteProfesor", professorID);
        } catch (Exception e) {
            throw new GenericException("An error occurred while deleting the professor: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Profesor> getProfesorById(int id) {
        return professorRepository.findById(id);
    }

    @Override
    public List<Profesor> getAllProfesors() {
        return professorRepository.findAll();
    }


//    @Override
//    public List<Profesor> getAllProfesorsByFacultyID(int facultyID) {
//        return profesorDAO.getAllProfesorsByFacultyID(facultyID);
//    }
//
//    @Override
//    public ProfessorsDTO getFacultyAndSubjects() {
//        return profesorDAO.getFacultyAndSubjects();
//    }
//
//    @Override
//    public ProfessorsDTO getProfessorToEdit(int id) {
//        return profesorDAO.getProfessorToEdit(id);
//    }
}
