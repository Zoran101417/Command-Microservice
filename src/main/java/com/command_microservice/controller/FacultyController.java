package com.command_microservice.controller;

import com.command_microservice.exception.GenericException;
import com.command_microservice.service.FacultyService;
import com.command_microservice.exception.CustomException;
import com.command_microservice.model.Faculty;
import com.command_microservice.service.FacultyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "command/api/faculty", produces = MediaType.APPLICATION_JSON_VALUE)
public class FacultyController {

    private final Logger LOG = LogManager.getLogger(FacultyController.class);

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @RequestMapping(method = RequestMethod.PUT)
//    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Object> updateFaculty(@RequestBody Faculty faculty) throws GenericException {
        Faculty updatedFaculty = facultyService.update(faculty);
        if (updatedFaculty != null) {
            LOG.error("The faculty was already updated, reload the record again");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The faculty was already updated, reload the record again");
        } else {
            LOG.error("The faculty was successfully updated");
            return ResponseEntity.status(HttpStatus.OK).body("The faculty was successfully updated");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @PreAuthorize("hasAnyAuthority('DELETE', 'DELETE ALL')")
    public ResponseEntity<Object> deleteFaculty(@PathVariable int id) throws GenericException {
        facultyService.delete(id);
//        if (deleteFlag > 0) {
//            LOG.info("The faculty was successfully deleted");
//            return ResponseEntity.status(HttpStatus.OK).body("The faculty was successfully deleted");
//        } else {
//            LOG.error("The faculty was not deleted");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The faculty was not deleted");
//        }
        return ResponseEntity.status(HttpStatus.OK).body("The faculty was successfully deleted");

    }

    @RequestMapping(method = RequestMethod.POST)
//    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Object> saveFaculty(@RequestBody Faculty faculty) throws GenericException {
        Faculty savedFaculty = facultyService.save(faculty);
        if (savedFaculty != null) {
            LOG.info("The faculty was successfully added");
            return ResponseEntity.status(HttpStatus.OK).body("The faculty was successfully added");
        } else {
            LOG.error("The faculty was not added");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The faculty was not added");
        }
    }

}
