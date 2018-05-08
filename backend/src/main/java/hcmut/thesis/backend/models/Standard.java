package hcmut.thesis.backend.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Standard {
    private int idStandard;
    private String stName;
    private Integer idUser;
    private int coefficient;
    private Integer semesterNo;

    @Id
    @Column(name = "id_standard")
    public int getIdStandard() {
        return idStandard;
    }

    public void setIdStandard(int idStandard) {
        this.idStandard = idStandard;
    }

    @Basic
    @Column(name = "st_name")
    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    @Basic
    @Column(name = "id_user")
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Standard standard = (Standard) o;
        return idStandard == standard.idStandard &&
                Objects.equals(stName, standard.stName) &&
                Objects.equals(idUser, standard.idUser);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idStandard, stName, idUser);
    }

    @Basic
    @Column(name = "coefficient")
    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    @Basic
    @Column(name = "semester_no")
    public Integer getSemesterNo() {
        return semesterNo;
    }

    public void setSemesterNo(Integer semesterNo) {
        this.semesterNo = semesterNo;
    }
}
