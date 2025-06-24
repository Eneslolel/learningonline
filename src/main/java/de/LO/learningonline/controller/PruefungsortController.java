package de.LO.learningonline.controller;

import de.LO.learningonline.model.Pruefungsort;
import de.LO.learningonline.repository.PruefungsortRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import jakarta.persistence.*;
import java.util.List;

@Controller
@RequestMapping("/pruefungsort")
public class PruefungsortController {

    private final PruefungsortRepository repo;
    public PruefungsortController(PruefungsortRepository repo) {
        this.repo = repo;
    }

    // 1) LIST ALL
    @GetMapping
    public String list(Model model) {
        List<Pruefungsort> alle = repo.findAll();
        model.addAttribute("orte", alle);
        return "pruefungsort/list";
    }

    // 2) SHOW FORM TO CREATE
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("ort", new Pruefungsort());
        return "pruefungsort/form";
    }

    // 3) HANDLE CREATE OR UPDATE
    @PostMapping
    public String save(@Valid @ModelAttribute("ort") Pruefungsort ort,
                       BindingResult br) {
        if (br.hasErrors()) return "pruefungsort/form";
        repo.save(ort);
        return "redirect:/pruefungsort";
    }

    // 4) SHOW FORM TO EDIT
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Pruefungsort ort = repo.findById(id).orElseThrow();
        model.addAttribute("ort", ort);
        return "pruefungsort/form";
    }

    // 5) DELETE ACTION
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/pruefungsort";
    }
}
