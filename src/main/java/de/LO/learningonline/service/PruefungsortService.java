package de.LO.learningonline.service;

import de.LO.learningonline.model.Pruefungsort;
import de.LO.learningonline.repository.PruefungsortRepository;
import de.LO.learningonline.service.UserLocationService;
import de.LO.learningonline.dto.PruefungsortDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PruefungsortService {

    private final PruefungsortRepository ortRepo;
    private final UserLocationService locationService;

    public PruefungsortService(
            PruefungsortRepository ortRepo,
            UserLocationService locationService
    ) {
        this.ortRepo = ortRepo;
        this.locationService = locationService;
    }

    /**
     * Liefert DTOs für Studierende
     */
    public List<PruefungsortDto> sucheFuerStudent(
            String ort,
            Long studiengang,
            LocalDate datum,
            String userEmail
    ) {
        return ortRepo.findByFilter(ort, studiengang, datum).stream()
                .map(p -> toDto(p, true, userEmail))
                .collect(Collectors.toList());
    }

    /**
     * Liefert DTOs für Dozenten
     */
    public List<PruefungsortDto> sucheFuerDozent(
            String ort,
            Long studiengang,
            LocalDate datum,
            String userEmail
    ) {
        return ortRepo.findByFilter(ort, studiengang, datum).stream()
                .map(p -> toDto(p, false, userEmail))
                .collect(Collectors.toList());
    }

    private PruefungsortDto toDto(Pruefungsort p, boolean isStudent, String email) {
        int belegt = isStudent
                ? ortRepo.countStudentAnmeldungen(p.getId())
                : ortRepo.countDozentAnmeldungen(p.getId());
        int maxPlaetze = p.getSitzplaetze();
        double entfernung = locationService.distance(email, p.getId());

        return new PruefungsortDto(
                p.getId(),
                p.getStadt(),
                p.getAdresse(),
                p.getDatum(),
                maxPlaetze - belegt,
                entfernung
        );
    }
}
