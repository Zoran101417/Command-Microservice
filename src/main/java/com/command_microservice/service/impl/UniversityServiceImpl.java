package com.command_microservice.service.impl;

import com.command_microservice.exception.GenericException;
import com.command_microservice.exception.KafkaException;
import com.command_microservice.model.University;
import com.command_microservice.repository.FacultyRepository;
import com.command_microservice.repository.UniversityRepository;
import com.command_microservice.service.UniversityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UniversityServiceImpl implements UniversityService {

    private static final Logger LOG = LogManager.getLogger(UniversityServiceImpl.class);

    private final UniversityRepository universityRepository;

    private final FacultyRepository facultyRepository;

    private final KafkaTemplate<String, Object> multiTypeKafkaTemplate;

    public UniversityServiceImpl(UniversityRepository universityRepository, FacultyRepository facultyRepository, KafkaTemplate<String, Object> multiTypeKafkaTemplate) {
        this.universityRepository = universityRepository;
        this.facultyRepository = facultyRepository;
        this.multiTypeKafkaTemplate = multiTypeKafkaTemplate;
    }

    @Override
    @Transactional(rollbackFor = GenericException.class)
    public University save(University university) throws Exception {
        University savedUniversity;
        try {
            savedUniversity = universityRepository.save(university);
            this.multiTypeKafkaTemplate.send("saveUniversity", university);
        } catch (Exception e) {
            throw new Exception("An error occurred while saving university: " + e.getMessage(), e);
        }
        return savedUniversity;
    }

    @Override
    @Transactional(rollbackFor = GenericException.class)
    public void delete(int universityID) throws Exception {
        try {
            universityRepository.deleteById(universityID);
            this.multiTypeKafkaTemplate.send("deleteUniversity", universityID);
        } catch (Exception e) {
            throw new Exception("An error occurred while deleting university: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional(rollbackFor = GenericException.class)
    public University update(University university) throws Exception {
        University updatedUniversity;
        try {
            updatedUniversity = universityRepository.save(university);
            this.multiTypeKafkaTemplate.send("saveUniversity", university);
        } catch (Exception e) {
            throw new Exception("An error occurred while updating university: " + e.getMessage(), e);
        }
        return updatedUniversity;
    }


    @Override
    public Optional<University> getUniversityById(int id) {
        return universityRepository.findById(id);
    }

}
