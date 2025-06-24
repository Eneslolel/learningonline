package de.LO.learningonline.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PRUEFUNGSORT")
public class Pruefungsort {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "STUDIENORT_ID")
    private Long studienortId;

    @Column(name = "STADT", nullable = false, length = 50)
    private String stadt;

    @Column(name = "PLZ", nullable = false)
    private Integer plz;

    @Column(name = "ADRESSE", nullable = false, length = 200)
    private String adresse;

    @Column(name = "SITZPLAETZE", nullable = false)
    private Integer sitzplaetze;

    public Pruefungsort() {
    }

    public Pruefungsort(Long id,
                        Long studienortId,
                        String stadt,
                        Integer plz,
                        String adresse,
                        Integer sitzplaetze) {
        this.id = id;
        this.studienortId = studienortId;
        this.stadt = stadt;
        this.plz = plz;
        this.adresse = adresse;
        this.sitzplaetze = sitzplaetze;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudienortId() {
        return studienortId;
    }
    public void setStudienortId(Long studienortId) {
        this.studienortId = studienortId;
    }

    public String getStadt() {
        return stadt;
    }
    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public Integer getPlz() {
        return plz;
    }
    public void setPlz(Integer plz) {
        this.plz = plz;
    }

    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getSitzplaetze() {
        return sitzplaetze;
    }
    public void setSitzplaetze(Integer sitzplaetze) {
        this.sitzplaetze = sitzplaetze;
    }
}
