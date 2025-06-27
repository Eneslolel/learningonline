package de.LO.learningonline.service;

import de.LO.learningonline.dto.AnmeldungErgebnisDto;
import de.LO.learningonline.dto.PruefungsortAnzeigeDto;
import de.LO.learningonline.model.Dozent;
import de.LO.learningonline.model.Geschrieben;
import de.LO.learningonline.model.Prueft;
import de.LO.learningonline.model.Student;
import de.LO.learningonline.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PruefungsortService {
    private final PruefungsortRepository ortRepo;
    private final StudentRepository studentRepo;
    private final DozentRepository dozentRepo;
    private final GeschriebenRepository geschriebenRepo;
    private final PrueftRepository prueftRepo;
    private final VeranstaltetRepository veranstaltetRepo;
    private final PruefungRepository pruefungRepo;

    // Konstruktor für Spring Dependency Injection
    public PruefungsortService(
            PruefungsortRepository ortRepo,
            StudentRepository studentRepo,
            DozentRepository dozentRepo,
            GeschriebenRepository geschriebenRepo,
            PrueftRepository prueftRepo,
            VeranstaltetRepository veranstaltetRepo,
            PruefungRepository pruefungRepo) {
        this.ortRepo = ortRepo;
        this.studentRepo = studentRepo;
        this.dozentRepo = dozentRepo;
        this.geschriebenRepo = geschriebenRepo;
        this.prueftRepo = prueftRepo;
        this.veranstaltetRepo = veranstaltetRepo;
        this.pruefungRepo = pruefungRepo;
    }

    public List<PruefungsortAnzeigeDto> sucheOrte(Long modulId, String ort, LocalDate datum, Long matrikelnr) {
        List<Object[]> rows = ortRepo.findFilteredOrte(modulId, ort, datum);
        List<PruefungsortAnzeigeDto> result = new ArrayList<>();
        for (Object[] r : rows) {
            Long pruefungsortId = ((Number) r[0]).longValue();
            String stadt = (String) r[1];
            String adresse = (String) r[2];
            LocalDate dat = (r[3] != null)
                    ? (r[3] instanceof java.sql.Timestamp)
                    ? ((java.sql.Timestamp) r[3]).toLocalDateTime().toLocalDate()
                    : ((r[3] instanceof java.sql.Date) ? ((java.sql.Date) r[3]).toLocalDate() : null)
                    : null;
            int sitzplaetze = ((Number) r[4]).intValue();
            int belegtePlaetze = ((Number) r[5]).intValue();
            int freiePlaetze = ((Number) r[6]).intValue();
            Long pruefungId = ((Number) r[7]).longValue();

            boolean anmeldbar = false;

            // --- Neue Logik: Nur wenn (a) zugelassen, (b) noch nicht angemeldet ---
            if (matrikelnr != null && pruefungId != null) {
                // (1) Existiert Eintrag: Zugelassen = 'Y' (also angemeldet & zugelassen)
                boolean schonAngemeldet = geschriebenRepo.existsByStudentMatrikelnrAndPruefungIdAndZugelassen(matrikelnr, pruefungId, "Y");
                // (2) Existiert Eintrag: Zugelassen = 'N' (abgelehnt)
                boolean abgelehnt = geschriebenRepo.existsByStudentMatrikelnrAndPruefungIdAndZugelassen(matrikelnr, pruefungId, "N");
                // (3) Existiert Eintrag: Zulassung = 'Y', aber noch keine Anmeldung? → in deinem Modell: nicht möglich, das ist identisch.
                // Ergo: ANMELDBAR, wenn es KEINEN Datensatz mit Y und KEINEN mit N gibt!
                anmeldbar = !schonAngemeldet && !abgelehnt;
            }

            result.add(new PruefungsortAnzeigeDto(
                    pruefungsortId, stadt, adresse, dat, sitzplaetze, belegtePlaetze, freiePlaetze, anmeldbar
            ));
        }
        return result;
    }

    public AnmeldungErgebnisDto studentAnmelden(String studentMail, Long pruefungsortId, LocalDate datum) {
        Student student = studentRepo.findByEmail(studentMail).orElse(null);
        if (student == null) return new AnmeldungErgebnisDto(false, "Student nicht gefunden!");

        Long pruefungId = findePruefungId(pruefungsortId, datum);
        if (pruefungId == null)
            return new AnmeldungErgebnisDto(false, "Kein Prüfungstermin am gewünschten Ort/Datum!");

        // Prüfe "zugelassen"
        if (geschriebenRepo.existsByStudentMatrikelnrAndPruefungIdAndZugelassen(student.getMatrikelnr(), pruefungId, "Y"))
            return new AnmeldungErgebnisDto(false, "Du bist bereits für diese Prüfung angemeldet.");
        if (geschriebenRepo.existsByStudentMatrikelnrAndPruefungIdAndZugelassen(student.getMatrikelnr(), pruefungId, "N"))
            return new AnmeldungErgebnisDto(false, "Du bist für diese Prüfung nicht zugelassen.");

        int belegte = geschriebenRepo.countByPruefungId(pruefungId);
        int maxPlaetze = ortRepo.findById(pruefungsortId).orElseThrow().getSitzplaetze();
        if (belegte >= maxPlaetze)
            return new AnmeldungErgebnisDto(false, "Der ausgewählte Prüfungsort ist bereits voll belegt.");

        geschriebenRepo.save(new Geschrieben(student.getMatrikelnr(), pruefungId, null, "Y"));
        return new AnmeldungErgebnisDto(true, "Anmeldung erfolgreich!");
    }

    // --- NEU: Anmeldung Prüfer ---
    public AnmeldungErgebnisDto prueferAnmelden(String dozentMail, Long pruefungsortId) {
        Dozent dozent = dozentRepo.findByEmail(dozentMail).orElse(null);
        if (dozent == null) return new AnmeldungErgebnisDto(false, "Dozent nicht gefunden!");
        Long prueferId = dozent.getPrueferId();
        if (prueferId == null) return new AnmeldungErgebnisDto(false, "Kein Prüferkonto vorhanden!");

        int belegte = prueftRepo.countByPruefungsortId(pruefungsortId);
        if (belegte >= 3)
            return new AnmeldungErgebnisDto(false, "Keine freien Plätze für Prüfer an diesem Ort.");

        // Prüfen, ob der Prüfer schon angemeldet ist
        if (prueftRepo.existsByPrueferIdAndPruefungsortId(prueferId, pruefungsortId))
            return new AnmeldungErgebnisDto(false, "Du bist bereits als Prüfer an diesem Ort angemeldet.");

        prueftRepo.save(new Prueft(prueferId, pruefungsortId));
        return new AnmeldungErgebnisDto(true, "Anmeldung als Prüfer erfolgreich!");

    }

    // --- Hilfsmethode ---
    private Long findePruefungId(Long pruefungsortId, LocalDate datum) {
        // Suche alle Prüfungen, die an diesem Ort stattfinden
        var veranstaltet = veranstaltetRepo.findByPruefungsortId(pruefungsortId);
        for (var v : veranstaltet) {
            var pruefungOpt = pruefungRepo.findById(v.getPruefungId());
            if (pruefungOpt.isPresent()) {
                var pruefung = pruefungOpt.get();
                if (pruefung.getDatum() != null && pruefung.getDatum().equals(datum))
                    return pruefung.getId();
            }
        }
        return null;
    }

}
