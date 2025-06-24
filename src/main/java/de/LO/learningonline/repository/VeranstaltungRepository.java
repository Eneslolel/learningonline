package de.LO.learningonline.repository;

import de.LO.learningonline.model.Veranstaltung;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeranstaltungRepository extends JpaRepository<Veranstaltung, Long> {
}
