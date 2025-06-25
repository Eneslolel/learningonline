package de.LO.learningonline.controller;

import de.LO.learningonline.dto.PruefungsortDto;
import de.LO.learningonline.model.Pruefungsort;
import de.LO.learningonline.model.Student;
import de.LO.learningonline.repository.PruefungsortRepository;
import de.LO.learningonline.repository.StudentRepository;
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
public class StudentController {

    private final StudentRepository studentRepo;
    private final PruefungsortRepository ortRepo;

    public StudentController(StudentRepository studentRepo,
                             PruefungsortRepository ortRepo) {
        this.studentRepo = studentRepo;
        this.ortRepo = ortRepo;
    }

    @GetMapping("/student/dashboard")
    public String dashboard(
            @RequestParam(required = false) String ort,
            @RequestParam(required = false) String studiengang,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datum,
            Model model,
            @AuthenticationPrincipal UserDetails user
    ) {
        // Suchparameter zurückgeben, damit das Formular sie behält:
        model.addAttribute("ort", ort);
        model.addAttribute("studiengang", studiengang);
        model.addAttribute("datum", datum);

        // Dein Service, der filtert und freie Plätze & Entfernung berechnet:
        List<PruefungsortDto> orte = pruefungsortService.sucheFuerStudent(ort, studiengang, datum, user.getUsername());
        model.addAttribute("orte", orte);

        return "student/student-dashboard";
    }

}
