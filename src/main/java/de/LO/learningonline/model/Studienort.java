package de.LO.learningonline.model;

import jakarta.persistence.*;

@Entity
@Table(name = "STUDIENORT")
public class Studienort {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "PRUEFUNGSORT_ID")
    private Long pruefungsortId;

    @Column(name = "STADT", nullable = false, length = 50)
    private String stadt;

    @Column(name = "PLZ", nullable = false)
    private Integer plz;

    @Column(name = "ADRESSE", nullable = false, length = 200)
    private String adresse;

    @Column(name = "EMAIL", nullable = false, length = 200)
    private String email;

    @Column(name = "INSTITUTION", nullable = false, length = 20)
    private String institution;

    @Column(name = "INSTITUTIONSNAME", nullable = false, length = 200)
    private String institutionsname;

    public Studienort() {}

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPruefungsortId() { return pruefungsortId; }
    public void setPruefungsortId(Long pruefungsortId) { this.pruefungsortId = pruefungsortId; }

    public String getStadt() { return stadt; }
    public void setStadt(String stadt) { this.stadt = stadt; }

    public Integer getPlz() { return plz; }
    public void setPlz(Integer plz) { this.plz = plz; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getInstitution() { return institution; }
    public void setInstitution(String institution) { this.institution = institution; }

    public String getInstitutionsname() { return institutionsname; }
    public void setInstitutionsname(String institutionsname) { this.institutionsname = institutionsname; }
}
