package de.LO.learningonline.controller;

import de.LO.learningonline.dto.PruefungsortDto;
import de.LO.learningonline.service.PruefungsortService;
import de.LO.learningonline.repository.DozentRepository;
import de.LO.learningonline.model.Dozent;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class DozentController {

    private final DozentRepository dozRepo;
    private final PruefungsortService ortService;

    public DozentController(
            DozentRepository dozRepo,
            PruefungsortService ortService
    ) {
        this.dozRepo = dozRepo;
        this.ortService = ortService;
    }

    @GetMapping("/dozent/dashboard")
    public String dashboard(
            @RequestParam(required = false) String ort,
            @RequestParam(required = false) Long studiengang,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate datum,
            @AuthenticationPrincipal UserDetails user,
            Model model
    ) {
        // Filter-Parameter mitschicken zur Ansicht
        model.addAttribute("ort", ort);
        model.addAttribute("studiengang", studiengang);
        model.addAttribute("datum", datum);

        // eingeloggter Dozent
        Dozent d = dozRepo.findByEmail(user.getUsername())
                .orElseThrow();

        // DTOs holen
        List<PruefungsortDto> orte = ortService
                .sucheFuerDozent(ort, studiengang, datum, user.getUsername());

        model.addAttribute("dozent", d);
        model.addAttribute("orte", orte);
        return "dozent/dozent-dashboard";
    }
}
