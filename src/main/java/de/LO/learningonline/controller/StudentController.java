package de.LO.learningonline.controller;

import de.LO.learningonline.model.Pruefungsort;
import de.LO.learningonline.model.Student;
import de.LO.learningonline.repository.PruefungsortRepository;
import de.LO.learningonline.repository.StudentRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
