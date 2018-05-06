package hcmut.thesis.backend.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "general_standard", schema = "thesis", catalog = "")
public class GeneralStandard {
    private int idGeneralStandard;
    private String stName;
    private int coefficient;
    private int semesterNo;

    @Id
    @Column(name = "id_general_standard")
    public int getIdGeneralStandard() {
        return idGeneralStandard;
    }

    public void setIdGeneralStandard(int idGeneralStandard) {
        this.idGeneralStandard = idGeneralStandard;
    }

    @Basic
    @Column(name = "stName")
    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
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
    public int getSemesterNo() {
        return semesterNo;
    }

    public void setSemesterNo(int semesterNo) {
        this.semesterNo = semesterNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralStandard that = (GeneralStandard) o;
        return idGeneralStandard == that.idGeneralStandard &&
                coefficient == that.coefficient &&
                semesterNo == that.semesterNo &&
                Objects.equals(stName, that.stName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idGeneralStandard, stName, coefficient, semesterNo);
    }
}
