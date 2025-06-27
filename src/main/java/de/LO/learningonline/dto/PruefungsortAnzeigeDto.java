package de.LO.learningonline.dto;

import java.time.LocalDate;

public class PruefungsortAnzeigeDto {
    private Long pruefungsortId;
    private String stadt;
    private String adresse;
    private LocalDate datum;
    private int sitzplaetze;
    private int belegtePlaetze;
    private int freiePlaetze;
    private boolean anmeldbar;
    private String modulFach;

    public Long getPruefungsortId() {
        return pruefungsortId;
    }

    public void setPruefungsortId(Long pruefungsortId) {
        this.pruefungsortId = pruefungsortId;
    }

    public boolean isAnmeldbar() {
        return anmeldbar;
    }

    public void setAnmeldbar(boolean anmeldbar) {
        this.anmeldbar = anmeldbar;
    }

    public String getStadt() {
        return stadt;
    }

    public String getModulFach() {
        return modulFach;
    }

    public void setModulFach(String modulFach) {
        this.modulFach = modulFach;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public int getSitzplaetze() {
        return sitzplaetze;
    }

    public void setSitzplaetze(int sitzplaetze) {
        this.sitzplaetze = sitzplaetze;
    }

    public int getBelegtePlaetze() {
        return belegtePlaetze;
    }

    public void setBelegtePlaetze(int belegtePlaetze) {
        this.belegtePlaetze = belegtePlaetze;
    }

    public int getFreiePlaetze() {
        return freiePlaetze;
    }

    public void setFreiePlaetze(int freiePlaetze) {
        this.freiePlaetze = freiePlaetze;
    }

    public PruefungsortAnzeigeDto(Long pruefungsortId, String stadt, String adresse, LocalDate datum,
                                  int sitzplaetze, int belegtePlaetze, int freiePlaetze, boolean anmeldbar, String modulFach) {
        this.pruefungsortId = pruefungsortId;
        this.stadt = stadt;
        this.adresse = adresse;
        this.datum = datum;
        this.sitzplaetze = sitzplaetze;
        this.belegtePlaetze = belegtePlaetze;
        this.freiePlaetze = freiePlaetze;
        this.anmeldbar = anmeldbar;
        this.modulFach = modulFach;
    }

}
