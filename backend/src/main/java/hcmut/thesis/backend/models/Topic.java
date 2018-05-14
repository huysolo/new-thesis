package hcmut.thesis.backend.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Topic {
    private int idTop;
    private String title;
    private int stNumLimit;
    private String sumary;
    private int idProf;
    private Integer score;
    private Integer semesterNo;
    private Integer idSpecialize;
    private Timestamp uploadDate;
    private Timestamp publishDate;
    private Integer studentCount;
    private Integer profScore;
    private Timestamp reviewDate;

    public Topic(int idTop, String title, int stNumLimit, String sumary, int idProf, Integer score, Integer semesterNo, Integer idSpecialize, Integer studentCount, Timestamp publishDate) {
        this.idTop = idTop;
        this.title = title;
        this.stNumLimit = stNumLimit;
        this.sumary = sumary;
        this.idProf = idProf;
        this.score = score;
        this.semesterNo = semesterNo;
        this.idSpecialize = idSpecialize;
        this.studentCount = studentCount;
        this.publishDate = publishDate;
    }

    public Topic() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_top")
    public int getIdTop() {
        return idTop;
    }

    public void setIdTop(int idTop) {
        this.idTop = idTop;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "st_num_limit")
    public int getStNumLimit() {
        return stNumLimit;
    }

    public void setStNumLimit(int stNumLimit) {
        this.stNumLimit = stNumLimit;
    }

    @Basic
    @Column(name = "sumary")
    public String getSumary() {
        return sumary;
    }

    public void setSumary(String sumary) {
        this.sumary = sumary;
    }

    @Basic
    @Column(name = "id_prof")
    public int getIdProf() {
        return idProf;
    }

    public void setIdProf(int idProf) {
        this.idProf = idProf;
    }

    @Basic
    @Column(name = "score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Basic
    @Column(name = "semester_no")
    public Integer getSemesterNo() {
        return semesterNo;
    }

    public void setSemesterNo(Integer semesterNo) {
        this.semesterNo = semesterNo;
    }

    @Basic
    @Column(name = "id_specialize")
    public Integer getIdSpecialize() {
        return idSpecialize;
    }

    public void setIdSpecialize(Integer idSpecialize) {
        this.idSpecialize = idSpecialize;
    }

    @Basic
    @Column(name = "upload_date")
    public Timestamp getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Timestamp uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Basic
    @Column(name = "publish_date")
    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return idTop == topic.idTop &&
                stNumLimit == topic.stNumLimit &&
                idProf == topic.idProf &&
                Objects.equals(title, topic.title) &&
                Objects.equals(sumary, topic.sumary) &&
                Objects.equals(score, topic.score) &&
                Objects.equals(semesterNo, topic.semesterNo) &&
                Objects.equals(idSpecialize, topic.idSpecialize) &&
                Objects.equals(uploadDate, topic.uploadDate) &&
                Objects.equals(publishDate, topic.publishDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idTop, title, stNumLimit, sumary, idProf, score, semesterNo, idSpecialize, uploadDate, publishDate);
    }

    @Basic
    @Column(name = "student_count")
    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    @Basic
    @Column(name = "prof_score")
    public Integer getProfScore() {
        return profScore;
    }

    public void setProfScore(Integer profScore) {
        this.profScore = profScore;
    }

    @Basic
    @Column(name = "review_date")
    public Timestamp getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Timestamp reviewDate) {
        this.reviewDate = reviewDate;
    }

}
