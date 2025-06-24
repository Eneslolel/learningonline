// src/main/java/de/LO/learningonline/repository/PruefungsortRepository.java
package de.LO.learningonline.repository;

import de.LO.learningonline.model.Pruefungsort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PruefungsortRepository extends JpaRepository<Pruefungsort, Long> {
    // für Studenten: alle Orte am selben Studienort-ID
    List<Pruefungsort> findByStudienortId(Long studienortId);

    // für Dozenten/Prüfer: alle Prüfungen, die dieser prüft
    List<Pruefungsort> findByPrueferId(Long prueferId);
}
