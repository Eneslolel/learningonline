package de.LO.learningonline.dto;

import java.time.LocalDate;

public class MeinePruefungDto {
    private Long pruefungId;
    private String modulName;
    private LocalDate datum;
    private String zugelassen;

    public MeinePruefungDto(Long pruefungId,String modulName, LocalDate datum, String zugelassen) {
        this.pruefungId = pruefungId;
        this.modulName = modulName;
        this.datum = datum;
        this.zugelassen = zugelassen;
    }
    public String getModulName() {
        return modulName;
    }

    public void setModulName(String modulName) {
        this.modulName = modulName;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public Long getPruefungId() {
        return pruefungId;
    }

    public void setPruefungId(Long pruefungId) {
        this.pruefungId = pruefungId;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getZugelassen() {
        return zugelassen;
    }

    public void setZugelassen(String zugelassen) {
        this.zugelassen = zugelassen;
    }
}

