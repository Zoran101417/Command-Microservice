package com.command_microservice.repository;

import com.command_microservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(int id);
    User findByUsernameAndPassword(String username, String password);
}
