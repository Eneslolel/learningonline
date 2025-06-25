package de.LO.learningonline.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "VERANSTALTET")
@IdClass(Veranstaltet.PK.class)
public class Veranstaltet implements Serializable {

    @Id
    @Column(name = "PRUEFUNGSORT_ID")
    private Long pruefungsortId;

    @Id
    @Column(name = "PRUEFUNG_ID")
    private Long pruefungId;

    public Veranstaltet() {}

    public Veranstaltet(Long pruefungsortId, Long pruefungId) {
        this.pruefungsortId = pruefungsortId;
        this.pruefungId = pruefungId;
    }

    public Long getPruefungsortId() {
        return pruefungsortId;
    }

    public void setPruefungsortId(Long pruefungsortId) {
        this.pruefungsortId = pruefungsortId;
    }

    public Long getPruefungId() {
        return pruefungId;
    }

    public void setPruefungId(Long pruefungId) {
        this.pruefungId = pruefungId;
    }
// Getter/Setter ...

    public static class PK implements Serializable {
        private Long pruefungsortId;
        private Long pruefungId;

        public PK() {}
        public PK(Long pruefungsortId, Long pruefungId) {
            this.pruefungsortId = pruefungsortId;
            this.pruefungId = pruefungId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PK pk)) return false;
            return pruefungsortId.equals(pk.pruefungsortId)
                    && pruefungId.equals(pk.pruefungId);
        }

        @Override
        public int hashCode() {
            return pruefungsortId.hashCode() + pruefungId.hashCode();
        }
    }
}
