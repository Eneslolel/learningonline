package de.LO.learningonline.service;

import de.LO.learningonline.repository.PruefungRepository;
import de.LO.learningonline.repository.PruefungsortRepository;
import de.LO.learningonline.repository.VeranstaltetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class PruefungService {
    private final PruefungRepository pruefungRepo;
    private final PruefungsortRepository pruefungsortRepo;
    private final VeranstaltetRepository veranstaltetRepo;

    @Autowired
    public PruefungService(
            PruefungRepository pruefungRepo,
            PruefungsortRepository pruefungsortRepo,
            VeranstaltetRepository veranstaltetRepo) {
        this.pruefungRepo = pruefungRepo;
        this.pruefungsortRepo = pruefungsortRepo;
        this.veranstaltetRepo = veranstaltetRepo;
    }

    public LocalDate getDatumById(Long pruefungId) {
        return pruefungRepo.findById(pruefungId)
                .map(p -> p.getDatum())
                .orElse(null);
    }
    // Gibt die Stadt (oder Adresse) des Prüfungsorts zurück
    public String getOrtByPruefungId(Long pruefungId) {
        return veranstaltetRepo.findByPruefungId(pruefungId)
                .flatMap(v -> pruefungsortRepo.findById(v.getPruefungsortId()))
                .map(ort -> ort.getStadt())
                .orElse("Unbekannt");
    }
}
