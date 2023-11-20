package com.command_microservice.service.impl;

import com.command_microservice.model.Course;
import com.command_microservice.repository.CourseRepository;
import com.command_microservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourses() {
        return null;
    }

    @Override
    public Optional<Course> getCourseById(int id) {
        return Optional.empty();
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course update(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void delete(int id) {
        courseRepository.deleteById((long) id);
    }
}
