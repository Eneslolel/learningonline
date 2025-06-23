package de.LO.learningonline;

import de.LO.learningonline.repository.ModulRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbTester implements CommandLineRunner {

    private final ModulRepository repo;

    public DbTester(ModulRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) throws Exception {
        // Anzahl der Kurse in der DB abfragen
        long count = repo.count();
        System.out.println("Es gibt " + count + " Kurse in der Datenbank.");
        // Optional: Alle Kurse ausgeben
        repo.findAll().forEach(k ->
                System.out.println(k.getId() + " → " + k.getFach()));
    }
}
