package com.command_microservice.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.StringIdGenerator.class,
        property="ID")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;
    private String name;
    private String lname;
//    @ManyToOne
//    @JoinColumn(name="facultyId")
////    @JsonBackReference
//    private Faculty faculty;
//    @OneToOne
//    @JoinColumn(name="subjectId")
//    private Subject subject;
    private int facultyID;
    private int subjectID;
    private int seq = 1;

}
