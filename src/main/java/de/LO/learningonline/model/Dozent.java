package de.LO.learningonline.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "DOZENT")
public class Dozent implements Serializable {

    @Id
    @Column(name = "MATRIKELNR", nullable = false)
    private Long matrikelNr;

    @Column(name = "PRUEFER_ID")
    private Long prueferId;            // optionaler Verweis, falls vorhanden

    @Column(name = "STUDIENORT_ID", nullable = false)
    private Long studienortId;

    @Column(name = "EMAIL", length = 200, nullable = false)
    private String email;

    @Column(name = "VORNAME", length = 80, nullable = false)
    private String vorname;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "AKADEMISCHER_GRAD", length = 200)
    private String akademischerGrad;

    @Column(name = "PASSWORT", length = 50, nullable = false)
    private String passwort;

    // Standard-Konstruktor (wird für JPA benötigt)
    public Dozent() {
    }

    // Voll-Argument-Konstruktor
    public Dozent(Long matrikelNr,
                  Long prueferId,
                  Long studienortId,
                  String email,
                  String vorname,
                  String name,
                  String akademischerGrad,
                  String passwort) {
        this.matrikelNr = matrikelNr;
        this.prueferId = prueferId;
        this.studienortId = studienortId;
        this.email = email;
        this.vorname = vorname;
        this.name = name;
        this.akademischerGrad = akademischerGrad;
        this.passwort = passwort;
    }

    // Getter & Setter

    public Long getMatrikelNr() {
        return matrikelNr;
    }

    public void setMatrikelNr(Long matrikelNr) {
        this.matrikelNr = matrikelNr;
    }

    public Long getPrueferId() {
        return prueferId;
    }

    public void setPrueferId(Long prueferId) {
        this.prueferId = prueferId;
    }

    public Long getStudienortId() {
        return studienortId;
    }

    public void setStudienortId(Long studienortId) {
        this.studienortId = studienortId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAkademischerGrad() {
        return akademischerGrad;
    }

    public void setAkademischerGrad(String akademischerGrad) {
        this.akademischerGrad = akademischerGrad;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}
