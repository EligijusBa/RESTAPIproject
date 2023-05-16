package com.balukonis.app.universityRESTAPI.universityserviceRestAPI.controller;

import com.balukonis.app.universityRESTAPI.universityserviceRestAPI.model.University;
import com.balukonis.app.universityRESTAPI.universityserviceRestAPI.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/universities")
public class UniversityController {
    @Autowired
    private UniversityService universityService;

    @GetMapping("/")
    public ResponseEntity<List<University>> getAllUniversities() {
        List<University> universities = universityService.getAllUniversities();
        return new ResponseEntity<>(universities, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<University> createUniversity(@RequestBody University university) {
        University createdUniversity = universityService.saveUniversity(university);
        return new ResponseEntity<>(createdUniversity, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<University> getUniversityById(@PathVariable Long id) {
        Optional<University> universityOptional = universityService.getUniversityById(id);
        University university = universityOptional.orElse(null);
        if (university == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(university, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<University> updateUniversity(@PathVariable Long id, @RequestBody University university) {
        University updatedUniversity = universityService.updateUniversity(id, university);
        return new ResponseEntity<>(updatedUniversity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUniversity(@PathVariable Long id) {
        universityService.deleteUniversity(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}