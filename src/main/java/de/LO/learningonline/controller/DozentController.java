// src/main/java/de/LO/learningonline/controller/DozentController.java
package de.LO.learningonline.controller;

import de.LO.learningonline.model.Dozent;
import de.LO.learningonline.model.Pruefungsort;
import de.LO.learningonline.repository.DozentRepository;
import de.LO.learningonline.repository.PruefungsortRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DozentController {

    private final DozentRepository dozRepo;
    private final PruefungsortRepository ortRepo;

    public DozentController(DozentRepository dozRepo,
                            PruefungsortRepository ortRepo) {
        this.dozRepo = dozRepo;
        this.ortRepo = ortRepo;
    }

    @GetMapping("/dozent/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails user, Model model) {
        Dozent d = dozRepo.findByEmail(user.getUsername()).orElseThrow();
        List<Pruefungsort> orte = ortRepo.findByPrueferId(d.getPrueferId());
        model.addAttribute("dozent", d);
        model.addAttribute("orte", orte);
        return "dozent-dashboard";
    }
}
