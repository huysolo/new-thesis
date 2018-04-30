package hcmut.thesis.backend.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class StudentTopicSemPK implements Serializable {
    private int idStudent;
    private int idTopicSem;

    @Column(name = "id_student")
    @Basic
    @Id
    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    @Column(name = "id_topic_sem")
    @Id
    public int getIdTopicSem() {
        return idTopicSem;
    }

    public void setIdTopicSem(int idTopicSem) {
        this.idTopicSem = idTopicSem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentTopicSemPK that = (StudentTopicSemPK) o;
        return idStudent == that.idStudent &&
                idTopicSem == that.idTopicSem;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idStudent, idTopicSem);
    }
}
