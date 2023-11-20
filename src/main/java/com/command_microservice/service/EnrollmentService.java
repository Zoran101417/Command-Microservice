package com.command_microservice.service;

import com.command_microservice.model.Enrollment;

import java.util.List;
import java.util.Optional;

public interface EnrollmentService {
    List<Enrollment> getAllEnrollments();
    Optional<Enrollment> getEnrollmentById(int id);
    Enrollment save(Enrollment enrollment);
    Enrollment update(Enrollment enrollment);
    void delete(long id);
}
