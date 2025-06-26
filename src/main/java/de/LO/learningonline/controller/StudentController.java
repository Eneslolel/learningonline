package de.LO.learningonline.controller;

import de.LO.learningonline.model.Pruefungsort;
import de.LO.learningonline.model.Student;
import de.LO.learningonline.repository.PruefungsortRepository;
import de.LO.learningonline.repository.StudentRepository;
import de.LO.learningonline.service.StudentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {

    private final StudentRepository studentRepo;
    private final PruefungsortRepository ortRepo;
    private final StudentService studentService;

    public StudentController(StudentRepository studentRepo,
                             PruefungsortRepository ortRepo,
                             StudentService studentService) {
        this.studentRepo = studentRepo;
        this.ortRepo = ortRepo;
        this.studentService = studentService;
    }

    @GetMapping("/student/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails user, Model model) {
        // Student aus DB laden
        Student s = studentRepo.findByEmail(user.getUsername())
                .orElseThrow();
        // Prüfungsorte am gleichen Studienort
        List<Pruefungsort> orte = ortRepo.findByStudienortId(s.getStudienortId());
        model.addAttribute("student", s);
        model.addAttribute("orte", orte);
        return "student-dashboard";      // Thymeleaf-Template student-dashboard.html
    }
    @GetMapping("/student/meine-pruefungen")
    public String meinePruefungen(@AuthenticationPrincipal UserDetails user, Model model) {
        // Hole Matrikelnummer
        var student = studentRepo.findByEmail(user.getUsername()).orElseThrow();
        Long matrikelnr = student.getMatrikelnr();

        // Hole alle Anmeldungen
        var anmeldungen = studentService.findeAlleAnmeldungenFuerStudent(matrikelnr);
        model.addAttribute("anmeldungen", anmeldungen);

        // Für Zusatzinfos (Datum, Ort ...) ggf. noch Listen zu Prüfungen und Orten
        // oder per DTO aggregieren (können wir gern zusammen bauen)

        return "meine-pruefungen";
    }
    @PostMapping("/student/abmelden")
    public String abmelden(@AuthenticationPrincipal UserDetails user, @RequestParam Long pruefungId) {
        // Student aus DB holen
        var student = studentRepo.findByEmail(user.getUsername()).orElseThrow();
        studentService.abmeldenVonPruefung(student.getMatrikelnr(), pruefungId);
        return "redirect:/student/meine-pruefungen";
    }
}
