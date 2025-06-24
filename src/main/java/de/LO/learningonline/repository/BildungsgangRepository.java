package de.LO.learningonline.repository;

import de.LO.learningonline.model.Bildungsgang;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BildungsgangRepository extends JpaRepository<Bildungsgang, Long> {
}
