package hcmut.thesis.backend.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class CommentTaskPK implements Serializable {
    private Timestamp time;
    private int idTask;
    private int idUser;

    @Column(name = "time")
    @Id
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Column(name = "id_task")
    @Id
    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    @Column(name = "id_user")
    @Id
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentTaskPK that = (CommentTaskPK) o;
        return idTask == that.idTask &&
                idUser == that.idUser &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {

        return Objects.hash(time, idTask, idUser);
    }
}
