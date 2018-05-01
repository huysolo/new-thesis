package hcmut.thesis.backend.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Standard {
    private int idStandard;
    private Integer stName;
    private Integer idUser;
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
    public Integer getStName() {
        return stName;
    }

    public void setStName(Integer stName) {
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

    @Basic
    @Column(name = "semester_no")
    public Integer getSemesterNo() {
        return semesterNo;
    }

    public void setSemesterNo(Integer semesterNo) {
        this.semesterNo = semesterNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Standard standard = (Standard) o;
        return idStandard == standard.idStandard &&
                Objects.equals(stName, standard.stName) &&
                Objects.equals(idUser, standard.idUser) &&
                Objects.equals(semesterNo, standard.semesterNo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idStandard, stName, idUser, semesterNo);
    }
}
