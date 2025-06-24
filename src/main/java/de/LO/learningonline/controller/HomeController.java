package de.LO.learningonline.controller;

import de.LO.learningonline.model.Modul;
import de.LO.learningonline.repository.ModulRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final ModulRepository repo;

    public HomeController(ModulRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Modul> alle = repo.findAll();
        model.addAttribute("module", alle);
        return "index";
    }
}
