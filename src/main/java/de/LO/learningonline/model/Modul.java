package de.LO.learningonline.model;

import jakarta.persistence.*;

@Entity
@Table(name = "MODUL")
public class Modul {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "modul_seq")
    @SequenceGenerator(
            name = "modul_seq",
            sequenceName = "KURS_SEQ",
            allocationSize = 1
    )
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "DOZENT_ID", nullable = false)
    private Long dozentId;

    @Column(name = "FACH", nullable = false, length = 120)
    private String fach;

    @Column(name = "SEMESTER", nullable = false)
    private Integer semester;

    @Column(name = "VERFUEGBAREECTS", nullable = false)
    private Integer verfuegbareEcts;

    public Modul() { }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getDozentId() {
        return dozentId;
    }
    public void setDozentId(Long dozentId) {
        this.dozentId = dozentId;
    }

    public String getFach() {
        return fach;
    }
    public void setFach(String fach) {
        this.fach = fach;
    }

    public Integer getSemester() {
        return semester;
    }
    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Integer getVerfuegbareEcts() {
        return verfuegbareEcts;
    }
    public void setVerfuegbareEcts(Integer verfuegbareEcts) {
        this.verfuegbareEcts = verfuegbareEcts;
    }
}
