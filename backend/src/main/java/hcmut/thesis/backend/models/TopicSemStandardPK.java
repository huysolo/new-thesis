package hcmut.thesis.backend.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TopicSemStandardPK implements Serializable {
    private int idTopicSem;
    private int idStandard;

    @Column(name = "id_topic_sem")
    @Id
    public int getIdTopicSem() {
        return idTopicSem;
    }

    public void setIdTopicSem(int idTopicSem) {
        this.idTopicSem = idTopicSem;
    }

    @Column(name = "id_standard")
    @Id
    public int getIdStandard() {
        return idStandard;
    }

    public void setIdStandard(int idStandard) {
        this.idStandard = idStandard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicSemStandardPK that = (TopicSemStandardPK) o;
        return idTopicSem == that.idTopicSem &&
                idStandard == that.idStandard;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idTopicSem, idStandard);
    }
}
