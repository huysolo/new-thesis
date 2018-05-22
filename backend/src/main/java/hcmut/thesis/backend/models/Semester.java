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
    private Timestamp beginDate;
    private Timestamp applyOpenDate;
    private Timestamp startDate;
    private Timestamp midtermReviewDate;
    private Timestamp reviewDate;
    private Timestamp closeDate;
    private Timestamp applyCloseDate;
    private Timestamp endDate;

    @Id
    @Column(name = "semester_no")
    public int getSemesterNo() {
        return semesterNo;
    }

    public void setSemesterNo(int semesterNo) {
        this.semesterNo = semesterNo;
    }

    @Basic
    @Column(name = "begin_date")
    public Timestamp getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Timestamp beginDate) {
        this.beginDate = beginDate;
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
    @Column(name = "start_date")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "midterm_review_date")
    public Timestamp getMidtermReviewDate() {
        return midtermReviewDate;
    }

    public void setMidtermReviewDate(Timestamp midtermReviewDate) {
        this.midtermReviewDate = midtermReviewDate;
    }

    @Basic
    @Column(name = "review_date")
    public Timestamp getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Timestamp reviewDate) {
        this.reviewDate = reviewDate;
    }

    @Basic
    @Column(name = "close_date")
    public Timestamp getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Timestamp closeDate) {
        this.closeDate = closeDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return semesterNo == semester.semesterNo &&
                Objects.equals(beginDate, semester.beginDate) &&
                Objects.equals(applyOpenDate, semester.applyOpenDate) &&
                Objects.equals(startDate, semester.startDate) &&
                Objects.equals(midtermReviewDate, semester.midtermReviewDate) &&
                Objects.equals(reviewDate, semester.reviewDate) &&
                Objects.equals(closeDate, semester.closeDate) &&
                Objects.equals(applyCloseDate, semester.applyCloseDate) &&
                Objects.equals(endDate, semester.endDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(semesterNo, beginDate, applyOpenDate, startDate, midtermReviewDate, reviewDate, closeDate, applyCloseDate, endDate);
    }
}
