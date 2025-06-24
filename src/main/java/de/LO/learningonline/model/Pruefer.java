package de.LO.learningonline.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PRUEFER")
public class Pruefer {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "DOZENT_MATRIKELNR")
    private Long dozentMatrikelnr;

    @Column(name = "EMAIL", nullable = false, length = 200)
    private String email;

    @Column(name = "VORNAME", nullable = false, length = 80)
    private String vorname;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    public Pruefer() {}

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getDozentMatrikelnr() { return dozentMatrikelnr; }
    public void setDozentMatrikelnr(Long dozentMatrikelnr) { this.dozentMatrikelnr = dozentMatrikelnr; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getVorname() { return vorname; }
    public void setVorname(String vorname) { this.vorname = vorname; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
