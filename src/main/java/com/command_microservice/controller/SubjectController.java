package com.command_microservice.controller;

import com.command_microservice.exception.GenericException;
import com.command_microservice.service.SubjectService;
import com.command_microservice.model.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "command/api/subject", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubjectController {

    private final Logger LOG = LogManager.getLogger(SubjectController.class);

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Object> updateSubject(@RequestBody Subject subject) throws GenericException {
        Subject updatedSubject = subjectService.update(subject);
        if(updatedSubject != null) {
            LOG.info("The Subject was successfully updated");
            return ResponseEntity.status(HttpStatus.OK).body("The Subject was successfully updated");
        } else {
            LOG.error("The subject was already updated, reload the subject data again!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The subject was already updated, reload the subject data again!");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasAnyAuthority('DELETE', 'DELETE ALL')")
    public ResponseEntity<Object> deleteSubject(@PathVariable int id) throws GenericException {
        subjectService.delete(id);
//        if(deleteFlag > 0) {
//            LOG.info("The subject was successfully deleted");
//            return ResponseEntity.status(HttpStatus.OK).body("The subject was successfully deleted");
//        } else {
//            LOG.error("The subject was not deleted");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The subject was not deleted");
//        }
        return ResponseEntity.status(HttpStatus.OK).body("The subject was successfully deleted");
    }

    @RequestMapping(method = RequestMethod.POST)
//    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Object> saveSubject(@RequestBody Subject subject) throws GenericException {
        Subject savedSubject = subjectService.save(subject);
        if(savedSubject != null) {
            LOG.info("The subject was successfully added");
            return ResponseEntity.status(HttpStatus.OK).body("The subject was successfully added");
        } else {
            LOG.error("The subject was not added");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The subject was not added");
        }
    }

}
