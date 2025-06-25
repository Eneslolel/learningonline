package de.LO.learningonline.repository;
import de.LO.learningonline.model.Geschrieben;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeschriebenRepository extends JpaRepository<Geschrieben, Long> {
    int countByPruefungId(Long pruefungId);
    boolean existsByStudentMatrikelnrAndPruefungId(Long studentMatrikelnr, Long pruefungId);
    boolean existsByStudentMatrikelnrAndPruefungIdAndZugelassen(Long studentMatrikelnr, Long pruefungId, String zugelassen);

}
