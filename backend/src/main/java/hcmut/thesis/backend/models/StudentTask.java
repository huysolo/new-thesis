package hcmut.thesis.backend.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "student_task", schema = "thesis", catalog = "")
@IdClass(StudentTaskPK.class)
public class StudentTask {
    public StudentTask(int idTask, int idStudent) {
        this.idTask = idTask;
        this.idStudent = idStudent;
    }

    private int idTask;
    private int idStudent;
    private Integer currentVersion;

    public StudentTask() {
    }

    @Id
    @Column(name = "id_task")
    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    @Id
    @Column(name = "id_student")
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
        StudentTask that = (StudentTask) o;
        return idTask == that.idTask &&
                idStudent == that.idStudent &&
                currentVersion.equals(that.currentVersion);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idTask, idStudent, currentVersion);
    }

    @Basic
    @Column(name = "current_version")
    public Integer getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(Integer currentVersion) {
        this.currentVersion = currentVersion;
    }
}
