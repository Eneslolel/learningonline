package de.LO.learningonline.repository;

import de.LO.learningonline.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // später spezielle Abfragen ergänzen.
    Optional<Student> findByEmail(String email);
}