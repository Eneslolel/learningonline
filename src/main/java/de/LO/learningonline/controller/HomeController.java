package de.LO.learningonline.controller;

import de.LO.learningonline.model.Modul;
import de.LO.learningonline.repository.ModulRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class HomeController {

    private final ModulRepository modulRepository;

    public HomeController(ModulRepository modulRepository) {
        this.modulRepository = modulRepository;
    }

    @GetMapping("/")
    public String home() {
        return "forward:/index.html";
    }
}
