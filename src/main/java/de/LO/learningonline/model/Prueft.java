package de.LO.learningonline.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PRUEFT")
@IdClass(Prueft.PK.class)
public class Prueft implements Serializable {

    @Id
    @Column(name = "PRUEFER_ID")
    private Long prueferId;

    @Id
    @Column(name = "PRUEFUNGSORT_ID")
    private Long pruefungsortId;

    public Prueft() {}

    public Prueft(Long prueferId, Long pruefungsortId) {
        this.prueferId = prueferId;
        this.pruefungsortId = pruefungsortId;
    }

    public Long getPrueferId() {
        return prueferId;
    }

    public void setPrueferId(Long prueferId) {
        this.prueferId = prueferId;
    }

    public Long getPruefungsortId() {
        return pruefungsortId;
    }

    public void setPruefungsortId(Long pruefungsortId) {
        this.pruefungsortId = pruefungsortId;
    }
// Getter/Setter ...

    public static class PK implements Serializable {
        private Long prueferId;
        private Long pruefungsortId;

        public PK() {}
        public PK(Long prueferId, Long pruefungsortId) {
            this.prueferId = prueferId;
            this.pruefungsortId = pruefungsortId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PK pk)) return false;
            return prueferId.equals(pk.prueferId)
                    && pruefungsortId.equals(pk.pruefungsortId);
        }

        @Override
        public int hashCode() {
            return prueferId.hashCode() + pruefungsortId.hashCode();
        }
    }
}
