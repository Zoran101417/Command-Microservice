package com.command_microservice.service.impl;

import com.command_microservice.model.Enrollment;
import com.command_microservice.repository.EnrollmentRepository;
import com.command_microservice.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Optional<Enrollment> getEnrollmentById(int id) {
        return enrollmentRepository.findById((long) id);
    }

    @Override
    public Enrollment save(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public Enrollment update(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public void delete(long id) {
        enrollmentRepository.deleteById(id);
    }
}
