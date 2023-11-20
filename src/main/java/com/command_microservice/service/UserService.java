package com.command_microservice.service;

import com.command_microservice.exception.CustomException;
import com.command_microservice.exception.GenericException;
import com.command_microservice.model.User;

import java.util.List;

public interface UserService {

    User save(User user) throws GenericException;

    User update(User user) throws GenericException;

    void delete(int facultyID) throws GenericException;

    List<User> getAllUsers();

    User getUserById(int id) throws CustomException;

    User getUserByNameAndPassword(String username, String password);

    List<String> getUserPermissionsByEmail(String userMail);
}
