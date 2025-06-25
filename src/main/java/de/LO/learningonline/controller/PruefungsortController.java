package de.LO.learningonline.controller;

import de.LO.learningonline.dto.PruefungsortAnzeigeDto;
import de.LO.learningonline.model.Modul;
import de.LO.learningonline.repository.ModulRepository;
import de.LO.learningonline.service.PruefungsortService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class PruefungsortController {

    private final PruefungsortService ortService;
    private final ModulRepository modulRepo;

    public PruefungsortController(PruefungsortService ortService, ModulRepository modulRepo) {
        this.ortService = ortService;
        this.modulRepo = modulRepo;
    }

    @GetMapping("/pruefungsorte")
    public String suche(
            @RequestParam(required = false) Long modulId,
            @RequestParam(required = false) String ort,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datum,
            Model model) {

        List<Modul> module = modulRepo.findAll();
        List<PruefungsortAnzeigeDto> orte = ortService.sucheOrte(modulId, ort, datum);

        model.addAttribute("module", module);
        model.addAttribute("orte", orte);

        return "pruefungsorte"; // Thymeleaf-Template
    }
}
