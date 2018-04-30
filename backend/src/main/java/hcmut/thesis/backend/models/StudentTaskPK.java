package hcmut.thesis.backend.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class StudentTaskPK implements Serializable {
    private int idTask;
    private int idStudent;

    @Column(name = "id_task")
    @Id
    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    @Column(name = "id_student")
    @Id
    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentTaskPK that = (StudentTaskPK) o;
        return idTask == that.idTask &&
                idStudent == that.idStudent;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idTask, idStudent);
    }
}
