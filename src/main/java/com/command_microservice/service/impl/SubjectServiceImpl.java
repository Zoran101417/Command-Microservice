package com.command_microservice.service.impl;

import com.command_microservice.exception.GenericException;
import com.command_microservice.exception.KafkaException;
import com.command_microservice.model.Subject;
import com.command_microservice.model.dto.SubjectDTO;
import com.command_microservice.repository.SubjectRepository;
import com.command_microservice.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    private final KafkaTemplate<String, Object> multiTypeKafkaTemplate;

    public SubjectServiceImpl(KafkaTemplate<String, Object> multiTypeKafkaTemplate) {
        this.multiTypeKafkaTemplate = multiTypeKafkaTemplate;
    }

    @Override
    @Transactional(rollbackFor = GenericException.class)
    public Subject save(Subject subject) throws GenericException {
        Subject savedSubject = null;
        try {
            savedSubject = subjectRepository.save(subject);
            this.multiTypeKafkaTemplate.send("saveSubject", subject);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            throw new GenericException("An error occurred while saving the subject: " + e.getMessage(), e);
        }
        return savedSubject;
    }

    @Override
    @Transactional(rollbackFor = GenericException.class)
    public void delete(int subjectID) throws GenericException {
        try {
            subjectRepository.deleteById(subjectID);
            this.multiTypeKafkaTemplate.send("deleteSubject", subjectID);
        } catch (Exception e) {
            throw new GenericException("An error occurred while deleting the subject: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional(rollbackFor = GenericException.class)
    public Subject update(Subject subject) throws GenericException {
        Subject updated;
        try {
            updated = subjectRepository.save(subject);
            this.multiTypeKafkaTemplate.send("saveSubject", subject);
        } catch (Exception e) {
            throw new GenericException("An error occurred while updating the subject: " + e.getMessage(), e);
        }
        return updated;
    }

    @Override
    public Subject getSubjectById(int id) {
//        return subjectDAO.getSubjectById(id);
        return null;
    }

    @Override
    public List<Subject> getAllSubjects() {
//        return subjectDAO.getAllSubjects();
        return null;
    }

    @Override
    public SubjectDTO getSubjectToEdit(int id) {
//        return subjectDAO.getSubjectToEdit(id);
        return null;
    }

}
