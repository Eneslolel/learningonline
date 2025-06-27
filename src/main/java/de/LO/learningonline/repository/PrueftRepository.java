package de.LO.learningonline.repository;

import de.LO.learningonline.model.Prueft;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrueftRepository extends JpaRepository<Prueft, Prueft.PK> {
    int countByPruefungsortId(Long pruefungsortId);
    boolean existsByPrueferIdAndPruefungsortId(Long prueferId, Long pruefungsortId);
    List<Prueft> findByPrueferId(Long prueferId);
    void deleteByPrueferIdAndPruefungsortId(Long prueferId, Long pruefungsortId);
}
