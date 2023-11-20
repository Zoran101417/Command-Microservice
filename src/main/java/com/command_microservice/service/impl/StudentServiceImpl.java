package com.command_microservice.service.impl;

import com.command_microservice.exception.GenericException;
import com.command_microservice.exception.KafkaException;
import com.command_microservice.model.Student;
import com.command_microservice.repository.StudentRepository;
import com.command_microservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    private final KafkaTemplate<String, Object> multiTypeKafkaTemplate;

    public StudentServiceImpl(KafkaTemplate<String, Object> multiTypeKafkaTemplate) {
        this.multiTypeKafkaTemplate = multiTypeKafkaTemplate;
    }

    @Override
    @Transactional(rollbackFor = GenericException.class)
    public Student save(Student student) throws GenericException {
        Student saved;
        try {
            saved = studentRepository.save(student);
            this.multiTypeKafkaTemplate.send("saveStudent", student);
        }catch (Exception e) {
            throw new GenericException("An error occurred while saving the student: " + e.getMessage(), e);
        }
        return saved;
    }

    @Override
    @Transactional(rollbackFor = GenericException.class)
    public void delete(int studentID) throws GenericException {
        try {
            studentRepository.deleteById(studentID);
            this.multiTypeKafkaTemplate.send("deleteStudent", studentID);
        }catch (Exception e) {
            throw new GenericException("An error occurred while deliting the student: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional(rollbackFor = GenericException.class)
    public Student update(Student student) throws GenericException {
        Student updatedStudent;
        try {
          updatedStudent = studentRepository.save(student);
          this.multiTypeKafkaTemplate.send("saveStudent", student);
        }catch (Exception e) {
            throw new GenericException("An error occurred while updating the student: " + e.getMessage(), e);
        }
        return updatedStudent;
    }

    @Override
    public Student getStudentById(int id) {
//        return studentDAO.getStudentById(id);
        return null;
    }

    @Override
    public List<Student> getAllStudents() {
//        return studentDAO.getAllStudents();
        return null;
    }


}
