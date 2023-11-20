package com.command_microservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.StringIdGenerator.class,
//        property="ID")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;
    private String name;
    private String lname;
    private String username;
    private String password;
    private String email;
    private int seq = 1;
//
//    @OneToMany(mappedBy = "userID", fetch = FetchType.EAGER)
//    private List<UserRole> roles;

}
