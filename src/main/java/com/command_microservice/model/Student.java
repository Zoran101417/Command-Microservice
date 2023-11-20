package com.command_microservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;
    private String name;
    private String lname;
//    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Faculty.class)
//    @JoinColumn(name = "facultyID")
//    @JsonBackReference
//    private Faculty faculty;
    private int facultyID;
    private int years;
    private int seq = 1;

//    @ManyToMany(cascade = {CascadeType.ALL})
//    @JoinTable(name = "student_subject",
//                joinColumns = { @JoinColumn(name = "studentID") },
//                inverseJoinColumns = { @JoinColumn(name = "subjectID")})
//    @JsonBackReference
//    private List<Subject> subjects;


}
