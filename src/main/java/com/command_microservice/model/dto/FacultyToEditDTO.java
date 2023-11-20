package com.command_microservice.model.dto;

import com.command_microservice.model.Faculty;
import com.command_microservice.model.University;

import java.util.List;

public class FacultyToEditDTO {
   private Faculty faculty;

   private List<University> universities;

   public Faculty getFaculty() {
      return faculty;
   }

   public void setFaculty(Faculty faculty) {
      this.faculty = faculty;
   }

   public List<University> getUniversities() {
      return universities;
   }

   public void setUniversities(List<University> universities) {
      this.universities = universities;
   }
}
