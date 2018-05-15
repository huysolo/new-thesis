package hcmut.thesis.backend.models;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "topic_mission", schema = "thesis", catalog = "")
public class TopicMission {
    private int idMission;
    private int idTopic;
    private String detail;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mission")
    public int getIdMission() {
        return idMission;
    }

    public void setIdMission(int idMission) {
        this.idMission = idMission;
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
        TopicMission that = (TopicMission) o;
        return idMission == that.idMission &&
                idTopic == that.idTopic &&
                Objects.equals(detail, that.detail);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idMission, idTopic, detail);
    }
}
