package com.command_microservice.service.impl;

import com.command_microservice.exception.GenericException;
import com.command_microservice.repository.FacultyRepository;
import com.command_microservice.service.FacultyService;
import com.command_microservice.exception.KafkaException;
import com.command_microservice.model.Faculty;
import com.command_microservice.model.dto.FacultyToEditDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    FacultyRepository facultyRepository;

    private final KafkaTemplate<String, Object> multiTypeKafkaTemplate;

    public FacultyServiceImpl(KafkaTemplate<String, Object> multiTypeKafkaTemplate) {
        this.multiTypeKafkaTemplate = multiTypeKafkaTemplate;
    }

    @Override
    @Transactional(rollbackFor = GenericException.class)
    public Faculty save(Faculty faculty) throws GenericException {
        Faculty saved = null;
        try {
            saved = facultyRepository.save(faculty);
            this.multiTypeKafkaTemplate.send("saveFaculty", faculty);
        } catch (Exception e) {
            throw new GenericException("An error occurred while saving the faculty: " + e.getMessage(), e);
        }
        return saved;
    }

    @Override
    @Transactional(rollbackFor = GenericException.class)
    public void delete(int facultyID) throws GenericException {
        try{
            facultyRepository.deleteById(facultyID);
            this.multiTypeKafkaTemplate.send("deleteFaculty", facultyID);
        } catch (Exception e) {
            throw new GenericException("An error occurred while deleting the faculty: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional(rollbackFor = GenericException.class)
    public Faculty update(Faculty faculty) throws GenericException {
        Faculty updatedFaculty;
        try{
            updatedFaculty = facultyRepository.save(faculty);
            this.multiTypeKafkaTemplate.send("saveFaculty", faculty);
        } catch (Exception e) {
            throw new GenericException("An error occurred while updating the faculty: " + e.getMessage(), e);
        }
        return updatedFaculty;
    }

    @Override
    public Optional<Faculty> getFacultyById(int id) {
        return facultyRepository.findById(id);
    }

    @Override
    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

}
