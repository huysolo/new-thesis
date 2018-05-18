package hcmut.thesis.backend.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CouncilUserPK implements Serializable {
    private int idCouncil;
    private int idUser;

    @Column(name = "id_council")
    @Id
    public int getIdCouncil() {
        return idCouncil;
    }

    public void setIdCouncil(int idCouncil) {
        this.idCouncil = idCouncil;
    }

    @Column(name = "id_user")
    @Id
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CouncilUserPK that = (CouncilUserPK) o;
        return idCouncil == that.idCouncil &&
                idUser == that.idUser;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCouncil, idUser);
    }
}
