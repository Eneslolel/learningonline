package de.LO.learningonline.controller;

import de.LO.learningonline.dto.PruefungsortAnzeigeDto;
import de.LO.learningonline.model.Modul;
import de.LO.learningonline.model.Student;
import de.LO.learningonline.model.Dozent;
import de.LO.learningonline.repository.ModulRepository;
import de.LO.learningonline.repository.StudentRepository;
import de.LO.learningonline.repository.DozentRepository;
import de.LO.learningonline.service.PruefungsortService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class PruefungsortController {

    private final PruefungsortService ortService;
    private final ModulRepository modulRepo;
    private final StudentRepository studentRepo;
    private final DozentRepository dozentRepo;

    public PruefungsortController(
            PruefungsortService ortService,
            ModulRepository modulRepo,
            StudentRepository studentRepo,
            DozentRepository dozentRepo
    ) {
        this.ortService = ortService;
        this.modulRepo = modulRepo;
        this.studentRepo = studentRepo;
        this.dozentRepo = dozentRepo;
    }

    @PostMapping("/pruefungsorte/anmelden")
    public String anmelden(
            @RequestParam Long pruefungsortId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datum,
            @AuthenticationPrincipal UserDetails user,
            RedirectAttributes redirectAttrs
    ) {
        String email = user.getUsername();
        String rolle = user.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_DOZENT")) ? "DOZENT" : "STUDENT";

        String feedback;
        boolean success;

        if (rolle.equals("STUDENT")) {
            var result = ortService.studentAnmelden(email, pruefungsortId, datum);
            feedback = result.getMessage();
            success = result.isSuccess();
        } else {
            var result = ortService.prueferAnmelden(email, pruefungsortId);
            feedback = result.getMessage();
            success = result.isSuccess();
        }

        redirectAttrs.addFlashAttribute("message", feedback);
        redirectAttrs.addFlashAttribute("success", success);

        return "redirect:/pruefungsorte";
    }

    @GetMapping("/pruefungsorte")
    public String suche(
            @RequestParam(required = false) Long modulId,
            @RequestParam(required = false) String ort,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datum,
            Model model,
            @AuthenticationPrincipal UserDetails user
    ) {
        List<Modul> module = modulRepo.findAll();
        Long matrikelnr = null;
        Long prueferId = null;

        if (user != null) {
            boolean isDozent = user.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_DOZENT"));
            if (isDozent) {
                Dozent dozent = dozentRepo.findByEmail(user.getUsername()).orElse(null);
                if (dozent != null) {
                    prueferId = dozent.getPrueferId(); // Oder ggf. dozent.getId(), je nach Modell
                }
            } else {
                Student student = studentRepo.findByEmail(user.getUsername()).orElse(null);
                if (student != null) {
                    matrikelnr = student.getMatrikelnr();
                }
            }
        }

        // matrikelnr und prueferId können auch null sein – dann ist niemand angemeldet
        List<PruefungsortAnzeigeDto> orte = ortService.sucheOrte(modulId, ort, datum, matrikelnr, prueferId);

        model.addAttribute("module", module);
        model.addAttribute("orte", orte);

        return "pruefungsorte";
    }
}
