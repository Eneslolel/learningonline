package de.LO.learningonline.model;

import jakarta.persistence.*;

@Entity
@Table(name = "BILDUNGSGANG")
public class Bildungsgang {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "STUDIENORT_ID", nullable = false)
    private Long studienortId;

    @Column(name = "UNTERSTUETZUNGSSOFTWARE_ID")
    private Long softwareId;

    @Column(name = "FACHBEREICH", nullable = false, length = 100)
    private String fachbereich;

    @Column(name = "REGELSTUDIENZEIT", nullable = false)
    private Integer regelstudienzeit;

    @Column(name = "AKADEMISCHER_ABSCHLUSS", nullable = false, length = 80)
    private String akademischerAbschluss;

    public Bildungsgang() {}

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStudienortId() { return studienortId; }
    public void setStudienortId(Long studienortId) { this.studienortId = studienortId; }

    public Long getSoftwareId() { return softwareId; }
    public void setSoftwareId(Long softwareId) { this.softwareId = softwareId; }

    public String getFachbereich() { return fachbereich; }
    public void setFachbereich(String fachbereich) { this.fachbereich = fachbereich; }

    public Integer getRegelstudienzeit() { return regelstudienzeit; }
    public void setRegelstudienzeit(Integer regelstudienzeit) { this.regelstudienzeit = regelstudienzeit; }

    public String getAkademischerAbschluss() { return akademischerAbschluss; }
    public void setAkademischerAbschluss(String akademischerAbschluss) { this.akademischerAbschluss = akademischerAbschluss; }
}
