package hcmut.thesis.backend.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(ReviewPK.class)
public class Review {
    private int idProf;
    private int idTopic;
    private Integer score;

    @Id
    @Column(name = "id_prof")
    public int getIdProf() {
        return idProf;
    }

    public void setIdProf(int idProf) {
        this.idProf = idProf;
    }

    @Id
    @Column(name = "id_topic")
    public int getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return idProf == review.idProf &&
                idTopic == review.idTopic;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idProf, idTopic);
    }

    @Basic
    @Column(name = "score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
