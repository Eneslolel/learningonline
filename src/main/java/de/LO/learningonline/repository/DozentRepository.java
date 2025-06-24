package de.LO.learningonline.repository;

import de.LO.learningonline.model.Dozent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DozentRepository extends JpaRepository<Dozent, Long> {
    Optional<Dozent> findByEmail(String email);
}
