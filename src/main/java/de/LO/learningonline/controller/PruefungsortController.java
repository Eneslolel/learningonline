package de.LO.learningonline.controller;

import de.LO.learningonline.dto.PruefungsortAnzeigeDto;
import de.LO.learningonline.model.Modul;
import de.LO.learningonline.model.Student;
import de.LO.learningonline.repository.ModulRepository;
import de.LO.learningonline.repository.StudentRepository;
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

    public PruefungsortController(
            PruefungsortService ortService,
            ModulRepository modulRepo,
            StudentRepository studentRepo
    ) {
        this.ortService = ortService;
        this.modulRepo = modulRepo;
        this.studentRepo = studentRepo;
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

        // Falls ein Student eingeloggt ist, seine Matrikelnummer ermitteln
        if (user != null) {
            Student student = studentRepo.findByEmail(user.getUsername()).orElse(null);
            if (student != null) {
                matrikelnr = student.getMatrikelnr();
            }
        }

        // matrikelnr kann auch null sein, dann wird einfach "anmeldbar" false sein für alle
        List<PruefungsortAnzeigeDto> orte = ortService.sucheOrte(modulId, ort, datum, matrikelnr);

        model.addAttribute("module", module);
        model.addAttribute("orte", orte);

        return "pruefungsorte";
    }
}
