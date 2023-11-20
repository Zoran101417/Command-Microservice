package com.command_microservice.controller;

import com.command_microservice.exception.GenericException;
import com.command_microservice.model.Faculty;
import com.command_microservice.model.Grade;
import com.command_microservice.service.GradeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "command/api/grade", produces = MediaType.APPLICATION_JSON_VALUE)
public class GradeController {

    private final Logger LOG = LogManager.getLogger(GradeController.class);

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @RequestMapping(method = RequestMethod.POST)
//    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Object> saveGrade(@RequestBody Grade grade) throws GenericException {
        Grade savedGrade = gradeService.save(grade);
        if (savedGrade != null) {
            LOG.info("The grade was successfully added");
            return ResponseEntity.status(HttpStatus.OK).body("The grade was successfully added");
        } else {
            LOG.error("The grade was not added");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The grade was not added");
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
//    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Object> updateGrade(@RequestBody Grade grade) throws GenericException {
        Grade updatedFaculty = gradeService.update(grade);
        if (updatedFaculty != null) {
            LOG.error("The grade was already updated, reload the record again");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The grade was already updated, reload the record again");
        } else {
            LOG.error("The grade was successfully updated");
            return ResponseEntity.status(HttpStatus.OK).body("The grade was successfully updated");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @PreAuthorize("hasAnyAuthority('DELETE', 'DELETE ALL')")
    public ResponseEntity<Object> deleteGrade(@PathVariable int id) throws GenericException {
        gradeService.delete(id);
//        if (deleteFlag > 0) {
//            LOG.info("The faculty was successfully deleted");
//            return ResponseEntity.status(HttpStatus.OK).body("The faculty was successfully deleted");
//        } else {
//            LOG.error("The faculty was not deleted");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The faculty was not deleted");
//        }
        return ResponseEntity.status(HttpStatus.OK).body("The grade was successfully deleted");

    }


}
