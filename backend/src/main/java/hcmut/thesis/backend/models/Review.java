package hcmut.thesis.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Objects;

@Entity
@IdClass(ReviewPK.class)
public class Review {
    private int idProf;
    private int idTopic;

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
}
