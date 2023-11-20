package com.command_microservice.controller;

import com.command_microservice.exception.GenericException;
import com.command_microservice.model.Course;
import com.command_microservice.model.Grade;
import com.command_microservice.service.CourseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "command/api/course", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {

    private final Logger LOG = LogManager.getLogger(CourseController.class);

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(method = RequestMethod.POST)
//    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Object> saveCourse(@RequestBody Course course) throws GenericException {
        Course savedCourse = courseService.save(course);
        if (savedCourse != null) {
            LOG.info("The course was successfully added");
            return ResponseEntity.status(HttpStatus.OK).body("The course was successfully added");
        } else {
            LOG.error("The course was not added");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The course was not added");
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
//    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Object> updateCourse(@RequestBody Course course) throws GenericException {
        Course updatedCourse = courseService.update(course);
        if (updatedCourse != null) {
            LOG.error("The course was already updated, reload the record again");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The course was already updated, reload the record again");
        } else {
            LOG.error("The course was successfully updated!");
            return ResponseEntity.status(HttpStatus.OK).body("The course was successfully updated");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @PreAuthorize("hasAnyAuthority('DELETE', 'DELETE ALL')")
    public ResponseEntity<Object> deleteCourse(@PathVariable int id) throws GenericException {
        courseService.delete(id);
//        if (deleteFlag > 0) {
//            LOG.info("The faculty was successfully deleted");
//            return ResponseEntity.status(HttpStatus.OK).body("The faculty was successfully deleted");
//        } else {
//            LOG.error("The faculty was not deleted");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The faculty was not deleted");
//        }
        return ResponseEntity.status(HttpStatus.OK).body("The course was successfully deleted");

    }


}
