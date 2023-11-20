package com.command_microservice.service.impl;

import com.command_microservice.exception.GenericException;
import com.command_microservice.repository.UserRepository;
import com.command_microservice.service.UserService;
import com.command_microservice.exception.CustomException;
import com.command_microservice.exception.KafkaException;
import com.command_microservice.model.User;
import com.command_microservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    private final KafkaTemplate<String, Object> multiTypeKafkaTemplate;

    public UserServiceImpl(KafkaTemplate<String, Object> multiTypeKafkaTemplate) {
        this.multiTypeKafkaTemplate = multiTypeKafkaTemplate;
    }

    @Override
    @Transactional(rollbackFor = GenericException.class)
    public User save(User user) throws GenericException {
        User createdUser;
        try {
            createdUser = userRepository.save(user);
            this.multiTypeKafkaTemplate.send("saveUser", user);
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
            throw new GenericException("An error occurred while saving the user: " + e, e);
        }
        return createdUser;
    }

    @Override
    @Transactional(rollbackFor = GenericException.class)
    public User update(User user) throws GenericException {
       User updatedUser =  userRepository.save(user);
        try {
            this.multiTypeKafkaTemplate.send("saveUser", user);
        }catch (Exception e) {
            throw new GenericException("An error occurred while updating the user: " + e, e);
        }
        return updatedUser;
    }

    @Override
    @Transactional(rollbackFor = GenericException.class)
    public void delete(int userID) throws GenericException {
        try {
            userRepository.deleteById(userID);
            this.multiTypeKafkaTemplate.send("deleteUser", userID);
        }catch (Exception e) {
            throw new GenericException("An error occurred while deleting the user: " + e, e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) throws CustomException {
        return userRepository.findById(id);
    }

    @Override
    public User getUserByNameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public List<String> getUserPermissionsByEmail(String userMail) {
        List<String> per = Arrays.asList("GET", "UPDATE","CREATE","ADMIN","DELETE","DELETE ALL");

//        return userRepository.getUserPermissionsByEmail(userMail);
        return per;
    }

}
