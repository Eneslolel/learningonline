package de.LO.learningonline.repository;

import de.LO.learningonline.model.Veranstaltet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface VeranstaltetRepository extends JpaRepository<Veranstaltet, Long> {
    List<Veranstaltet> findByPruefungsortId(Long pruefungsortId);
    Optional<Veranstaltet> findByPruefungId(Long pruefungId);
}

