package de.LO.learningonline.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "GESCHRIEBEN")
@IdClass(Geschrieben.PK.class)
public class Geschrieben implements Serializable {

    @Id
    @Column(name = "STUDENT_MATRIKEL_NR")
    private Long studentMatrikelnr;

    @Id
    @Column(name = "PRUEFUNG_ID")
    private Long pruefungId;

    @Column(name = "BENOTUNG")
    private java.math.BigDecimal benotung;

    @Column(name = "ZUGELASSEN", length = 1, columnDefinition = "CHAR(1)")
    private String zugelassen;

    // Default-Konstruktor für JPA
    public Geschrieben() {}

    // Komfortabler Konstruktor für Anmeldung
    public Geschrieben(Long studentMatrikelnr, Long pruefungId, java.math.BigDecimal benotung, String zugelassen) {
        this.studentMatrikelnr = studentMatrikelnr;
        this.pruefungId = pruefungId;
        this.benotung = benotung;
        this.zugelassen = zugelassen;
    }

    // Composite Primary Key Klasse
    public static class PK implements Serializable {
        private Long studentMatrikelnr;
        private Long pruefungId;

        public PK() {}
        public PK(Long studentMatrikelnr, Long pruefungId) {
            this.studentMatrikelnr = studentMatrikelnr;
            this.pruefungId = pruefungId;
        }

        // Get/Set Methoden
        public Long getStudentMatrikelnr() {
            return studentMatrikelnr;
        }

        public void setStudentMatrikelnr(Long studentMatrikelnr) {
            this.studentMatrikelnr = studentMatrikelnr;
        }

        public Long getPruefungId() {
            return pruefungId;
        }

        public void setPruefungId(Long pruefungId) {
            this.pruefungId = pruefungId;
        }

        // equals() & hashCode() implementieren!
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PK pk)) return false;
            return studentMatrikelnr.equals(pk.studentMatrikelnr)
                    && pruefungId.equals(pk.pruefungId);
        }

        @Override
        public int hashCode() {
            return studentMatrikelnr.hashCode() + pruefungId.hashCode();
        }
    }
    // Getter/Setter für Entity-Felder (ganz unten einfügen!)

    public Long getStudentMatrikelnr() {
        return studentMatrikelnr;
    }

    public void setStudentMatrikelnr(Long studentMatrikelnr) {
        this.studentMatrikelnr = studentMatrikelnr;
    }

    public Long getPruefungId() {
        return pruefungId;
    }

    public void setPruefungId(Long pruefungId) {
        this.pruefungId = pruefungId;
    }

    public java.math.BigDecimal getBenotung() {
        return benotung;
    }

    public void setBenotung(java.math.BigDecimal benotung) {
        this.benotung = benotung;
    }

    public String getZugelassen() {
        return zugelassen;
    }

    public void setZugelassen(String zugelassen) {
        this.zugelassen = zugelassen;
    }

}
