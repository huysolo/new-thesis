package hcmut.thesis.backend.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Review {
    private Integer idProf;
    private int idTopic;
    private Double score;
    private int submitted;
    private int idReview;
    private Integer idCouncil;

    public void setScore(Double score) {
        this.score = score;
    }

    @Basic
    @Column(name = "id_prof")
    public Integer getIdProf() {
        return idProf;
    }

    public void setIdProf(Integer idProf) {
        this.idProf = idProf;
    }

    @Basic
    @Column(name = "id_topic")
    public int getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
    }

    @Basic
    @Column(name = "score")
    public Double getScore() {
        return score;
    }

    @Basic
    @Column(name = "submitted")
    public int getSubmitted() {
        return submitted;
    }

    public void setSubmitted(int submitted) {
        this.submitted = submitted;
    }

    @Id
    @Column(name = "id_review")
    public int getIdReview() {
        return idReview;
    }

    public void setIdReview(int idReview) {
        this.idReview = idReview;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return idProf == review.idProf &&
                idTopic == review.idTopic &&
                submitted == review.submitted &&
                idReview == review.idReview &&
                Objects.equals(score, review.score);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idProf, idTopic, score, submitted, idReview);
    }

    @Basic
    @Column(name = "id_council")
    public Integer getIdCouncil() {
        return idCouncil;
    }

    public void setIdCouncil(Integer idCouncil) {
        this.idCouncil = idCouncil;
    }
}
