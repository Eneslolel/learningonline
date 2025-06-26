package de.LO.learningonline.repository;
import de.LO.learningonline.model.Geschrieben;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeschriebenRepository extends JpaRepository<Geschrieben, Long> {
    int countByPruefungId(Long pruefungId);
    boolean existsByStudentMatrikelnrAndPruefungId(Long studentMatrikelnr, Long pruefungId);
    boolean existsByStudentMatrikelnrAndPruefungIdAndZugelassen(Long studentMatrikelnr, Long pruefungId, String zugelassen);
    List<Geschrieben> findByStudentMatrikelnr(Long studentMatrikelnr);
    void deleteByStudentMatrikelnrAndPruefungId(Long studentMatrikelnr, Long pruefungId);

}
