package de.LO.learningonline.repository;

import de.LO.learningonline.model.Modul;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModulRepository extends JpaRepository<Modul, Long> {
    // Standard-CRUD-Methoden sind schon dabei:
    // findAll(), findById(), save(), deleteById(), …

    // Beispiel für eine eigene Abfrage:
    // List<Kurs> findByNameContainingIgnoreCase(String teilName);
}
