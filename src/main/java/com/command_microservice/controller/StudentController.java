package com.command_microservice.controller;

import com.command_microservice.exception.GenericException;
import com.command_microservice.service.StudentService;
import com.command_microservice.model.Student;
import com.command_microservice.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "command/api/student", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

    private final Logger LOG = LogManager.getLogger(StudentController.class);

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Object> updateStudent(@RequestBody Student student) throws GenericException {
        Student updatedStudent = studentService.update(student);
        if(updatedStudent != null) {
            LOG.error("The student was already changed, reload the information again!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The student was already changed, reload the information again!");
        } else {
            LOG.error("The student was successfully updated");
            return ResponseEntity.status(HttpStatus.OK).body("The student was successfully updated");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasAnyAuthority('DELETE', 'DELETE ALL')")
    public ResponseEntity<Object> deleteStudent(@PathVariable int id) throws GenericException {
       studentService.delete(id);
//        if(deleteFlag > 0) {
//            LOG.info("The student was successfully deleted");
//            return ResponseEntity.status(HttpStatus.OK).body("The student was successfully deleted");
//        } else {
//            LOG.error("The student was not deleted");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The student was not deleted");
//        }
        return ResponseEntity.status(HttpStatus.OK).body("The student was successfully deleted");
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Object> saveStudent(@RequestBody Student student) throws GenericException {
        Student savedStudent = studentService.save(student);
        if(savedStudent != null) {
            LOG.info("The student was successfully added");
            return ResponseEntity.status(HttpStatus.OK).body("The student was successfully added");
        } else {
            LOG.error("The student was not added");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The student was not added");
        }
    }

}
