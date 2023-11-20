package com.command_microservice.service.impl;

import com.command_microservice.model.Grade;
import com.command_microservice.repository.GradeRepository;
import com.command_microservice.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Override
    public List<Grade> getAllGrades() {
        return null;
    }

    @Override
    public Optional<Grade> getGradeById(int id) {
        return Optional.empty();
    }

    @Override
    public Grade save(Grade grade) {
        return gradeRepository.save(grade);
    }

    @Override
    public Grade update(Grade grade) {
        return gradeRepository.save(grade);
    }

    @Override
    public void delete(int id) {
        gradeRepository.deleteById((long) id);
    }

}
