package com.command_microservice.repository;

import com.command_microservice.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Profesor, Integer> {
}
