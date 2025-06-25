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
    SELECT 
        o.ID AS PRUEFUNGSORT_ID,
        o.STADT,
        o.ADRESSE,
        pr.DATUM,
        o.SITZPLAETZE,
        COUNT(g.STUDENT_MATRIKEL_NR) AS BELEGTE,
        (o.SITZPLAETZE - COUNT(g.STUDENT_MATRIKEL_NR)) AS FREIE
    FROM 
        PRUEFUNGSORT o
        JOIN VERANSTALTET v ON v.PRUEFUNGSORT_ID = o.ID
        JOIN PRUEFUNG pr ON pr.ID = v.PRUEFUNG_ID
        LEFT JOIN GESCHRIEBEN g ON g.PRUEFUNG_ID = pr.ID
    WHERE 
        (:modulId IS NULL OR pr.MODUL_ID = :modulId)
        AND (:ort IS NULL OR LOWER(o.STADT) LIKE LOWER('%' || :ort || '%'))
        AND (:datum IS NULL OR pr.DATUM = :datum)
    GROUP BY 
        o.ID, o.STADT, o.ADRESSE, pr.DATUM, o.SITZPLAETZE
    """, nativeQuery = true)
    List<Object[]> findFilteredOrte(
            @Param("modulId") Long modulId,
            @Param("ort") String ort,
            @Param("datum") LocalDate datum
    );
}
