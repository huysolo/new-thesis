package hcmut.thesis.backend.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "topic_requirement", schema = "thesis", catalog = "")
public class TopicRequirement {
    private int idReq;
    private int idTopic;
    private String detail;

    @Id
    @Column(name = "id_req")
    public int getIdReq() {
        return idReq;
    }

    public void setIdReq(int idReq) {
        this.idReq = idReq;
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
    @Column(name = "detail")
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicRequirement that = (TopicRequirement) o;
        return idReq == that.idReq &&
                idTopic == that.idTopic &&
                Objects.equals(detail, that.detail);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idReq, idTopic, detail);
    }
}
