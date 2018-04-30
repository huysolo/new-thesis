package hcmut.thesis.backend.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Specialize {
    private int idSpecialize;
    private int idFalcuty;
    private String name;

    @Id
    @Column(name = "id_specialize")
    public int getIdSpecialize() {
        return idSpecialize;
    }

    public void setIdSpecialize(int idSpecialize) {
        this.idSpecialize = idSpecialize;
    }

    @Basic
    @Column(name = "id_falcuty")
    public int getIdFalcuty() {
        return idFalcuty;
    }

    public void setIdFalcuty(int idFalcuty) {
        this.idFalcuty = idFalcuty;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specialize that = (Specialize) o;
        return idSpecialize == that.idSpecialize &&
                idFalcuty == that.idFalcuty &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idSpecialize, idFalcuty, name);
    }
}
