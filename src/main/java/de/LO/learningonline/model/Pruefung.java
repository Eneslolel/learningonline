package de.LO.learningonline.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRUEFUNG")
public class Pruefung {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "MODUL_ID", nullable = false)
    private Long modulId;

    @Column(name = "PRUEFUNGSBEGINN", nullable = false)
    private LocalDateTime beginn;

    @Column(name = "PRUEFUNGSENDE", nullable = false)
    private LocalDateTime ende;

    @Column(name = "DATUM")
    private LocalDate datum;

    public Pruefung() {}

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getModulId() { return modulId; }
    public void setModulId(Long modulId) { this.modulId = modulId; }

    public LocalDateTime getBeginn() { return beginn; }
    public void setBeginn(LocalDateTime beginn) { this.beginn = beginn; }

    public LocalDateTime getEnde() { return ende; }
    public void setEnde(LocalDateTime ende) { this.ende = ende; }

    public LocalDate getDatum() { return datum; }
    public void setDatum(LocalDate datum) { this.datum = datum; }
}
