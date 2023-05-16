package com.balukonis.app.universityRESTAPI.universityserviceRestAPI.repository;

import com.balukonis.app.universityRESTAPI.universityserviceRestAPI.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findById(long id);
}
