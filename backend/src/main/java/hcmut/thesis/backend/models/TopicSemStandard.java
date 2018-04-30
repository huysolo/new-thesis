package hcmut.thesis.backend.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "topic_sem_standard", schema = "thesis", catalog = "")
@IdClass(TopicSemStandardPK.class)
public class TopicSemStandard {
    private int idTopicSem;
    private int idStandard;
    private int score;

    @Id
    @Column(name = "id_topic_sem")
    public int getIdTopicSem() {
        return idTopicSem;
    }

    public void setIdTopicSem(int idTopicSem) {
        this.idTopicSem = idTopicSem;
    }

    @Id
    @Column(name = "id_standard")
    public int getIdStandard() {
        return idStandard;
    }

    public void setIdStandard(int idStandard) {
        this.idStandard = idStandard;
    }

    @Basic
    @Column(name = "score")
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicSemStandard that = (TopicSemStandard) o;
        return idTopicSem == that.idTopicSem &&
                idStandard == that.idStandard &&
                score == that.score;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idTopicSem, idStandard, score);
    }
}
