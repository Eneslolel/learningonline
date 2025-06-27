// src/main/java/de/LO/learningonline/controller/DozentController.java
package de.LO.learningonline.controller;

import de.LO.learningonline.model.Dozent;
import de.LO.learningonline.model.Prueft;
import de.LO.learningonline.model.Pruefungsort;
import de.LO.learningonline.repository.DozentRepository;
import de.LO.learningonline.repository.PrueftRepository;
import de.LO.learningonline.repository.PruefungsortRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DozentController {

    private final DozentRepository dozRepo;
    private final PruefungsortRepository ortRepo;
    private final PrueftRepository prueftRepo;

    public DozentController(DozentRepository dozRepo,
                            PruefungsortRepository ortRepo,
                            PrueftRepository prueftRepo) {
        this.dozRepo = dozRepo;
        this.ortRepo = ortRepo;
        this.prueftRepo = prueftRepo;
    }

    @GetMapping("/dozent/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails user, Model model) {
        Dozent d = dozRepo.findByEmail(user.getUsername()).orElseThrow();
        List<Pruefungsort> orte = ortRepo.findByPrueferId(d.getPrueferId());
        model.addAttribute("dozent", d);
        model.addAttribute("orte", orte);
        return "dozent-dashboard";
    }

    @GetMapping("/pruefer/meine-pruefungen")
    public String meinePruefungen(@AuthenticationPrincipal UserDetails user, Model model) {
        Dozent pruefer = dozRepo.findByEmail(user.getUsername()).orElseThrow();
        Long prueferId = pruefer.getPrueferId();

        List<Prueft> anmeldungen = prueftRepo.findByPrueferId(prueferId);
        // Du kannst hier noch DTOs bauen, wenn du mehr anzeigen willst

        // Beispiel: Hole zu jedem Pruefungsort
        List<Pruefungsort> orte = anmeldungen.stream()
                .map(a -> ortRepo.findById(a.getPruefungsortId()).orElse(null))
                .toList();

        model.addAttribute("orte", orte);
        return "pruefer-meine-pruefungen"; // eigenes Thymeleaf-Template
    }

    @Transactional
    @PostMapping("/pruefer/abmelden")
    public String abmelden(@AuthenticationPrincipal UserDetails user, @RequestParam Long pruefungsortId) {
        Dozent pruefer = dozRepo.findByEmail(user.getUsername()).orElseThrow();
        Long prueferId = pruefer.getPrueferId();

        prueftRepo.deleteByPrueferIdAndPruefungsortId(prueferId, pruefungsortId);
        return "redirect:/pruefer/meine-pruefungen";
    }
}
