package de.LO.learningonline.repository;

import de.LO.learningonline.model.Pruefungsort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PruefungsortRepository extends JpaRepository<Pruefungsort, Long> {

    // 1) Filter-Suche: Ort, Studiengang, Datum
    @Query("""
        select p from Pruefungsort p
         join p.studienortId s
         join s.bildungsgang b
        where (:ort is null or p.stadt = :ort)
          and (:studiengang is null or b.id = :studiengang)
          and (:datum is null or trunc(p.datum) = :datum)
        """)
    List<Pruefungsort> findByFilter(
            @Param("ort") String ort,
            @Param("studiengang") Long studiengang,
            @Param("datum") LocalDate datum
    );

    // 2) Wie viele Studenten haben sich für diesen Ort angemeldet?
    @Query("select count(g) from Geschrieben g where g.pruefungsort.id = :ortId")
    int countStudentAnmeldungen(@Param("ortId") Long ortId);

    // 3) Wie viele Dozenten/Gutachter haben sich für diesen Ort angemeldet?
    @Query("select count(v) from Veranstaltet v where v.pruefungsort.id = :ortId")
    int countDozentAnmeldungen(@Param("ortId") Long ortId);
}
