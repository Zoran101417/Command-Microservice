package com.command_microservice.service;

import com.command_microservice.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> getAllCourses();
    Optional<Course> getCourseById(int id);
    Course save(Course course);
    Course update(Course course);
    void delete(int id);
}
