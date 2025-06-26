package de.LO.learningonline.service;

import de.LO.learningonline.dto.MeinePruefungDto;
import de.LO.learningonline.model.Geschrieben;
import de.LO.learningonline.model.Modul;
import de.LO.learningonline.model.Pruefung;
import de.LO.learningonline.repository.GeschriebenRepository;
import de.LO.learningonline.repository.ModulRepository;
import de.LO.learningonline.repository.PruefungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final PruefungRepository pruefungRepo;
    private final ModulRepository modulRepo;
    private final GeschriebenRepository geschriebenRepo;

    @Autowired
    public StudentService(PruefungRepository pruefungRepo, ModulRepository modulRepo, GeschriebenRepository geschriebenRepo) {
        this.pruefungRepo = pruefungRepo;
        this.modulRepo = modulRepo;
        this.geschriebenRepo = geschriebenRepo;
    }

    public List<MeinePruefungDto> findeAlleAnmeldungenFuerStudent(Long matrikelnr) {
        List<Geschrieben> anmeldungen = geschriebenRepo.findByStudentMatrikelnr(matrikelnr);
        List<MeinePruefungDto> result = new ArrayList<>();
        for (Geschrieben g : anmeldungen) {
            Pruefung pruefung = pruefungRepo.findById(g.getPruefungId()).orElse(null);
            String modulFach = "";
            if (pruefung != null) {
                Modul modul = modulRepo.findById(pruefung.getModulId()).orElse(null);
                if (modul != null) {
                    modulFach = modul.getFach();
                }
            }
            // Beispiel: Du kannst noch weitere Felder ins DTO geben
            result.add(new MeinePruefungDto(
                    g.getPruefungId(),
                    modulFach,
                    pruefung != null ? pruefung.getDatum() : null,
                    g.getZugelassen()
            ));

        }
        return result;
    }
}
