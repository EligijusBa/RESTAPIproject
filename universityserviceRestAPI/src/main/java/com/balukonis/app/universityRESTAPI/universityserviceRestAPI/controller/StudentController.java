package com.balukonis.app.universityRESTAPI.universityserviceRestAPI.controller;

import com.balukonis.app.universityRESTAPI.universityserviceRestAPI.model.Student;
import com.balukonis.app.universityRESTAPI.universityserviceRestAPI.model.University;
import com.balukonis.app.universityRESTAPI.universityserviceRestAPI.service.StudentService;
import com.balukonis.app.universityRESTAPI.universityserviceRestAPI.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private UniversityService universityService;

    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Long universityId = student.getUniversity().getId();
        Optional<University> universityOptional = universityService.getUniversityById(universityId);
        University university = universityOptional.orElse(null);
        if (university == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        student.setUniversity(university);
        Student createdStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> studentOptional = studentService.getStudentById(id);
        Student student = studentOptional.orElse(null);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Optional<Student> studentOptional = studentService.getStudentById(id);
        if (studentOptional.isPresent()) {
            Student existingStudent = studentOptional.get();

            // Exclude university field from the update process
            student.setUniversity(existingStudent.getUniversity());

            existingStudent.setName(student.getName());
            existingStudent.setSurname(student.getSurname());
            existingStudent.setAge(student.getAge());
            existingStudent.setSubject(student.getSubject());

            studentService.updateStudent(id, existingStudent);
            return new ResponseEntity<>(existingStudent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteStudent (@PathVariable Long id){
            studentService.deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

