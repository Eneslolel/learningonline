// src/main/java/de/LO/learningonline/repository/PruefungsortRepository.java
package de.LO.learningonline.repository;

import de.LO.learningonline.model.Pruefungsort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PruefungsortRepository extends JpaRepository<Pruefungsort, Long> {
    // für Studenten: alle Orte am selben Studienort-ID
    List<Pruefungsort> findByStudienortId(Long studienortId);

    // für Dozenten/Prüfer: alle Prüfungen, die dieser prüft
    List<Pruefungsort> findByPrueferId(Long prueferId);
    @Query(value = """
        SELECT p.ID, p.STADT, p.ADRESSE, pr.DATUM, p.SITZPLAETZE,
               COALESCE((SELECT COUNT(*) FROM PRUEFUNGSANMELDUNG a
                         WHERE a.PRUEFUNGSORT_ID = p.ID AND a.DATUM = pr.DATUM), 0) as BELEGTE,
               (p.SITZPLAETZE - COALESCE((SELECT COUNT(*) FROM PRUEFUNGSANMELDUNG a
                         WHERE a.PRUEFUNGSORT_ID = p.ID AND a.DATUM = pr.DATUM), 0)) as FREIE
        FROM PRUEFUNGSORT p
        JOIN PRUEFUNG pr ON pr.PRUEFUNGSORT_ID = p.ID
        WHERE (:modulId IS NULL OR pr.MODUL_ID = :modulId)
          AND (:ort IS NULL OR p.STADT ILIKE %:ort%)
          AND (:datum IS NULL OR pr.DATUM = :datum)
        """, nativeQuery = true)
    List<Object[]> findFilteredOrte(
            @Param("modulId") Long modulId,
            @Param("ort") String ort,
            @Param("datum") LocalDate datum
    );
}
