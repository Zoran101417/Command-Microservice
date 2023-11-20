package com.command_microservice.repository;

import com.command_microservice.model.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Integer> {
}
