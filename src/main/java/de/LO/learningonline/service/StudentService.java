package de.LO.learningonline.service;

import de.LO.learningonline.model.Geschrieben;
import de.LO.learningonline.repository.GeschriebenRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final GeschriebenRepository geschriebenRepo;

    public StudentService(GeschriebenRepository geschriebenRepo) {
        this.geschriebenRepo = geschriebenRepo;
    }
    public List<Geschrieben> findeAlleAnmeldungenFuerStudent(Long matrikelnr) {
        return geschriebenRepo.findByStudentMatrikelnr(matrikelnr);
    }
}
