package hcmut.thesis.backend.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ReviewPK implements Serializable {
    private int idProf;
    private int idTopic;

    @Column(name = "id_prof")
    @Id
    public int getIdProf() {
        return idProf;
    }

    public void setIdProf(int idProf) {
        this.idProf = idProf;
    }

    @Column(name = "id_topic")
    @Id
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
        ReviewPK reviewPK = (ReviewPK) o;
        return idProf == reviewPK.idProf &&
                idTopic == reviewPK.idTopic;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idProf, idTopic);
    }
}
