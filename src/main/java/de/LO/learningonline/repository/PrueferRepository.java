package de.LO.learningonline.repository;

import de.LO.learningonline.model.Pruefer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrueferRepository extends JpaRepository<Pruefer, Long> {
    // zusätzliche Query-Methoden bei Bedarf, z.B. findByEmail(...)
}
