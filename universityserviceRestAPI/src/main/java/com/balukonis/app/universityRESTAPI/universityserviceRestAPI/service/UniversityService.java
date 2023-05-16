package com.balukonis.app.universityRESTAPI.universityserviceRestAPI.service;

import com.balukonis.app.universityRESTAPI.universityserviceRestAPI.model.University;
import com.balukonis.app.universityRESTAPI.universityserviceRestAPI.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    public University saveUniversity(University university) {
        return universityRepository.save(university);
    }

    public University updateUniversity(Long id, University university) {
        return universityRepository.save(university);
    }

    public void deleteUniversity(Long id) {
        universityRepository.deleteById(id);
    }

    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    public Optional<University> getUniversityById(Long id) {
        return universityRepository.findById(id);
    }
}

