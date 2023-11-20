package com.command_microservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "student_subject")
public class STDSubject {
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @ManyToOne
    @JoinColumn(name = "subjectID")
    @JsonBackReference
    private Subject subject;
    @ManyToOne
    @JoinColumn(name = "studentID")
    private Student student;


}
