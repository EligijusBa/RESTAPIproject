package com.balukonis.app.universityRESTAPI.universityserviceRestAPI.repository;

import com.balukonis.app.universityRESTAPI.universityserviceRestAPI.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
}
