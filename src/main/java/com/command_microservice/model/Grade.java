package com.command_microservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int grade;
    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;
}
