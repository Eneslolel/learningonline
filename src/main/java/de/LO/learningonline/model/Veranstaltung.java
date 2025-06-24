package de.LO.learningonline.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "VERANSTALTUNG")
public class Veranstaltung {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "MODUL_ID", nullable = false)
    private Long modulId;

    @Column(name = "VERANSTALTUNGSTYP", nullable = false, length = 20)
    private String veranstaltungstyp;

    @Column(name = "VERANSTALTUNGSBEGINN", nullable = false)
    private LocalDateTime beginn;

    @Column(name = "VERANSTALTUNGSDAUER", nullable = false)
    private Integer dauer;

    public Veranstaltung() {}

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getModulId() { return modulId; }
    public void setModulId(Long modulId) { this.modulId = modulId; }

    public String getVeranstaltungstyp() { return veranstaltungstyp; }
    public void setVeranstaltungstyp(String veranstaltungstyp) { this.veranstaltungstyp = veranstaltungstyp; }

    public LocalDateTime getBeginn() { return beginn; }
    public void setBeginn(LocalDateTime beginn) { this.beginn = beginn; }

    public Integer getDauer() { return dauer; }
    public void setDauer(Integer dauer) { this.dauer = dauer; }
}
