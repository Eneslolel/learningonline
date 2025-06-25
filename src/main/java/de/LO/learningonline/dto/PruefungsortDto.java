package de.LO.learningonline.dto;

import java.time.LocalDate;

/**
 * DTO für die Anzeige eines Prüfungsorts samt
 * freier Plätze und Entfernung.
 */
public class PruefungsortDto {

    private Long id;
    private String adresse;
    private LocalDate datum;
    private int freieStudentenPlaetze;
    private int freieDozentenPlaetze;
    private double entfernung;

    public PruefungsortDto(Long id,
                           String adresse,
                           LocalDate datum,
                           int freieStudentenPlaetze,
                           int freieDozentenPlaetze,
                           double entfernung) {
        this.id = id;
        this.adresse = adresse;
        this.datum = datum;
        this.freieStudentenPlaetze = freieStudentenPlaetze;
        this.freieDozentenPlaetze = freieDozentenPlaetze;
        this.entfernung = entfernung;
    }

    // --- Getter/Setter ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public LocalDate getDatum() { return datum; }
    public void setDatum(LocalDate datum) { this.datum = datum; }

    public int getFreieStudentenPlaetze() { return freieStudentenPlaetze; }
    public void setFreieStudentenPlaetze(int freieStudentenPlaetze) {
        this.freieStudentenPlaetze = freieStudentenPlaetze;
    }

    public int getFreieDozentenPlaetze() { return freieDozentenPlaetze; }
    public void setFreieDozentenPlaetze(int freieDozentenPlaetze) {
        this.freieDozentenPlaetze = freieDozentenPlaetze;
    }

    public double getEntfernung() { return entfernung; }
    public void setEntfernung(double entfernung) { this.entfernung = entfernung; }
}
