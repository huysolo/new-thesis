package hcmut.thesis.backend.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "council_user", schema = "thesis", catalog = "")
@IdClass(CouncilUserPK.class)
public class CouncilUser {
    private int idCouncil;
    private int idUser;
    private int role;

    @Id
    @Column(name = "id_council")
    public int getIdCouncil() {
        return idCouncil;
    }

    public void setIdCouncil(int idCouncil) {
        this.idCouncil = idCouncil;
    }

    @Id
    @Column(name = "id_user")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "role")
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CouncilUser that = (CouncilUser) o;
        return idCouncil == that.idCouncil &&
                idUser == that.idUser &&
                role == that.role;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCouncil, idUser, role);
    }
}
