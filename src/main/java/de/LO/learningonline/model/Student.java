package de.LO.learningonline.model;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    @Column(name = "MATRIKELNR", nullable = false)
    private Long matrikelnr;

    @Column(name = "STUDIENORT_ID", nullable = false)
    private Long studienortId;

    @Column(name = "EMAIL", nullable = false, length = 200)
    private String email;

    @Column(name = "VORNAME", nullable = false, length = 80)
    private String vorname;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "STUDIENBEGINN", nullable = false)
    private Date studienbeginn;

    @Column(name = "ERRIECHTEETCS", nullable = false)
    private Integer erreichteEtcs;

    @Column(name = "ZUGELASSEN", columnDefinition = "CHAR(1)", nullable = true)
    private String zugelassen;

    // --- neu: Login-Felder ---
    @Column(name = "PASSWORT", nullable = true, length = 100)
    private String passwort;

    // --- Konstruktoren ---

    public Student() {
    }

    public Student(Long matrikelnr, Long studienortId, String email,
                   String vorname, String name, Date studienbeginn,
                   Integer erreichteEtcs, String zugelassen, String passwort) {
        this.matrikelnr     = matrikelnr;
        this.studienortId   = studienortId;
        this.email          = email;
        this.vorname        = vorname;
        this.name           = name;
        this.studienbeginn  = studienbeginn;
        this.erreichteEtcs  = erreichteEtcs;
        this.zugelassen     = zugelassen;
        this.passwort       = passwort;
    }

    // --- Getter / Setter ---

    public Long getMatrikelnr() {
        return matrikelnr;
    }

    public void setMatrikelnr(Long matrikelnr) {
        this.matrikelnr = matrikelnr;
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

    public Date getStudienbeginn() {
        return studienbeginn;
    }

    public void setStudienbeginn(Date studienbeginn) {
        this.studienbeginn = studienbeginn;
    }

    public Integer getErreichteEtcs() {
        return erreichteEtcs;
    }

    public void setErreichteEtcs(Integer erreichteEtcs) {
        this.erreichteEtcs = erreichteEtcs;
    }

    public String getZugelassen() {
        return zugelassen;
    }

    public void setZugelassen(String zugelassen) {
        this.zugelassen = zugelassen;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}

