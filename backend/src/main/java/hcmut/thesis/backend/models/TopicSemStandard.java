package hcmut.thesis.backend.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "topic_sem_standard", schema = "thesis", catalog = "")
public class TopicSemStandard {
    private int idTopicSemStandard;
    private int score;
    private String content;
    private int coefficient;
    private int idReview;

    @Id
    @Column(name = "id_topic_sem_standard")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdTopicSemStandard() {
        return idTopicSemStandard;
    }

    public void setIdTopicSemStandard(int idTopicSemStandard) {
        this.idTopicSemStandard = idTopicSemStandard;
    }

    @Basic
    @Column(name = "score")
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        TopicSemStandard that = (TopicSemStandard) o;
        return idTopicSemStandard == that.idTopicSemStandard &&
                score == that.score &&
                coefficient == that.coefficient &&
                idReview == that.idReview &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idTopicSemStandard, score, content, coefficient, idReview);
    }
}
