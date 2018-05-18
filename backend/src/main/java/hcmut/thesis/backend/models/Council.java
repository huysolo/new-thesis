package hcmut.thesis.backend.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Council {
    private int idCouncil;
    private Timestamp createDate;
    private String councilName;

    @Id
    @Column(name = "id_council")
    public int getIdCouncil() {
        return idCouncil;
    }

    public void setIdCouncil(int idCouncil) {
        this.idCouncil = idCouncil;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "council_name")
    public String getCouncilName() {
        return councilName;
    }

    public void setCouncilName(String councilName) {
        this.councilName = councilName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Council council = (Council) o;
        return idCouncil == council.idCouncil &&
                Objects.equals(createDate, council.createDate) &&
                Objects.equals(councilName, council.councilName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCouncil, createDate, councilName);
    }
}
