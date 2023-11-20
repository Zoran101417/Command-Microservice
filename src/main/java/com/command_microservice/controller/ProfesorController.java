package com.command_microservice.controller;

import com.command_microservice.exception.GenericException;
import com.command_microservice.service.ProfesorService;
import com.command_microservice.exception.CustomException;
import com.command_microservice.model.Profesor;
import com.command_microservice.service.ProfesorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("command/api/professor")
public class ProfesorController {

    private final Logger LOG = LogManager.getLogger(ProfesorController.class);

    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @RequestMapping(method = RequestMethod.PUT)
//    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Object> updateProfessor(@RequestBody Profesor profesor) throws GenericException {
        Profesor updatedProfessor = profesorService.update(profesor);
        if(updatedProfessor != null) {
            LOG.error("The professor was already updated, reload the record again");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The professor was already updated, reload the record again");
        } else {
            LOG.error("The professor was successfully updated");
            return ResponseEntity.status(HttpStatus.OK).body("The professor was successfully updated");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @PreAuthorize("hasAnyAuthority('DELETE','DELETE ALL')")
    public ResponseEntity<Object> deleteProfessor(@PathVariable int id) throws GenericException {
        profesorService.delete(id);
//        if(deleteFlag > 0) {
//            LOG.info("The professor was successfully deleted");
//            return ResponseEntity.status(HttpStatus.OK).body("The professor was successfully deleted");
//        } else {
//            LOG.error("The professor was not deleted");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The professor was not deleted");
//        }
        return ResponseEntity.status(HttpStatus.OK).body("The professor was successfully deleted");
    }

    @RequestMapping(method = RequestMethod.POST)
//    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Object> saveProfessor(@RequestBody Profesor profesor) throws GenericException {
        Profesor addedProfessor = profesorService.save(profesor);
        if(addedProfessor != null) {
            LOG.info("The professor was successfully added");
            return ResponseEntity.status(HttpStatus.OK).body("The professor was successfully added");
        } else {
            LOG.error("The professor was not added");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The professor was not added");
        }
    }

}
