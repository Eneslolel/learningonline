package de.LO.learningonline.service;

import de.LO.learningonline.dto.PruefungsortAnzeigeDto;
import de.LO.learningonline.repository.PruefungsortRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PruefungsortService {
    private final PruefungsortRepository repo;

    public PruefungsortService(PruefungsortRepository repo) {
        this.repo = repo;
    }

    public List<PruefungsortAnzeigeDto> sucheOrte(Long modulId, String ort, LocalDate datum) {
        List<Object[]> rows = repo.findFilteredOrte(modulId, ort, datum);
        List<PruefungsortAnzeigeDto> result = new ArrayList<>();
        for (Object[] r : rows) {
            result.add(new PruefungsortAnzeigeDto(
                    ((Number) r[0]).longValue(),
                    (String) r[1],
                    (String) r[2],
                    (r[3] != null) ? ((java.sql.Date) r[3]).toLocalDate() : null,
                    ((Number) r[4]).intValue(),
                    ((Number) r[5]).intValue(),
                    ((Number) r[6]).intValue()
            ));
        }
        return result;
    }
}
