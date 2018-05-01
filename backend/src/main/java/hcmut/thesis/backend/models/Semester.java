package hcmut.thesis.backend.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Semester {
    private int semesterNo;
    private Timestamp applyOpenDate;
    private Timestamp applyCloseDate;
    private Timestamp endDate;
    private Timestamp startDate;
    private Timestamp closeDate;

    @Id
    @Column(name = "semester_no")
    public int getSemesterNo() {
        return semesterNo;
    }

    public void setSemesterNo(int semesterNo) {
        this.semesterNo = semesterNo;
    }

    @Basic
    @Column(name = "apply_open_date")
    public Timestamp getApplyOpenDate() {
        return applyOpenDate;
    }

    public void setApplyOpenDate(Timestamp applyOpenDate) {
        this.applyOpenDate = applyOpenDate;
    }

    @Basic
    @Column(name = "apply_close_date")
    public Timestamp getApplyCloseDate() {
        return applyCloseDate;
    }

    public void setApplyCloseDate(Timestamp applyCloseDate) {
        this.applyCloseDate = applyCloseDate;
    }

    @Basic
    @Column(name = "end_date")
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "start_date")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return semesterNo == semester.semesterNo &&
                Objects.equals(applyOpenDate, semester.applyOpenDate) &&
                Objects.equals(applyCloseDate, semester.applyCloseDate) &&
                Objects.equals(endDate, semester.endDate) &&
                Objects.equals(startDate, semester.startDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(semesterNo, applyOpenDate, applyCloseDate, endDate, startDate);
    }

    @Basic
    @Column(name = "close_date")
    public Timestamp getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Timestamp closeDate) {
        this.closeDate = closeDate;
    }
}
