package de.LO.learningonline.repository;

import de.LO.learningonline.model.Pruefer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrueferRepository extends JpaRepository<Pruefer, Long> {
    Optional<Pruefer> findByEmail(String email);
}
