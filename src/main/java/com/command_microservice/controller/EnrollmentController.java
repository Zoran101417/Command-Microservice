package com.command_microservice.controller;

import com.command_microservice.exception.GenericException;
import com.command_microservice.model.Enrollment;
import com.command_microservice.service.EnrollmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "command/api/enrollment", produces = MediaType.APPLICATION_JSON_VALUE)
public class EnrollmentController {

    private final Logger LOG = LogManager.getLogger(EnrollmentController.class);

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @RequestMapping(method = RequestMethod.POST)
//    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Object> saveEnrollment(@RequestBody Enrollment enrollment) throws GenericException {
        Enrollment savedEnrollment = enrollmentService.save(enrollment);
        if (savedEnrollment != null) {
            LOG.info("The course was successfully added");
            return ResponseEntity.status(HttpStatus.OK).body("The course was successfully added");
        } else {
            LOG.error("The course was not added");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The course was not added");
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
//    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Object> updateEnrollment(@RequestBody Enrollment enrollment) throws GenericException {
        Enrollment updatedEnrollment = enrollmentService.update(enrollment);
        if (updatedEnrollment != null) {
            LOG.error("The enrollment was already updated, reload the record again");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The enrollment was already updated, reload the record again");
        } else {
            LOG.error("The enrollment was successfully updated");
            return ResponseEntity.status(HttpStatus.OK).body("The enrollment was successfully updated");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @PreAuthorize("hasAnyAuthority('DELETE', 'DELETE ALL')")
    public ResponseEntity<Object> deleteEnrollment(@PathVariable int id) throws GenericException {
        enrollmentService.delete(id);
//        if (deleteFlag > 0) {
//            LOG.info("The faculty was successfully deleted");
//            return ResponseEntity.status(HttpStatus.OK).body("The faculty was successfully deleted");
//        } else {
//            LOG.error("The faculty was not deleted");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The faculty was not deleted");
//        }
        return ResponseEntity.status(HttpStatus.OK).body("The enrollment was successfully deleted");
    }


}
