package com.command_microservice.service;

import com.command_microservice.model.University;

import java.util.Optional;


public interface UniversityService {

    University save(University university) throws Exception;

    void delete(int universityID) throws Exception;

    Optional<University> getUniversityById(int id);

    University update(University university) throws Exception;


}
