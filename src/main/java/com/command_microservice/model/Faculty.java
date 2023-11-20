package com.command_microservice.model;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.StringIdGenerator.class,
//        property="ID")
@Getter
@Setter
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;
    private String name;

//    @JsonBackReference

//    @ManyToOne
//    @JoinColumn(name = "universityID")
//    private University university;

//    @Column(name = "universityID", insertable = false, updatable = false)

    private int universityID;

    private int seq = 1;

}
