package de.LO.learningonline.service;

import de.LO.learningonline.model.Student;
import de.LO.learningonline.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository repo;
    public StudentService(StudentRepository repo) { this.repo = repo; }
    public List<Student> findAll()     { return repo.findAll(); }
    public Optional<Student> find(Long id) { return repo.findById(id); }
    public Student save(Student s)     { return repo.save(s); }
    public void delete(Long id)        { repo.deleteById(id); }
}
