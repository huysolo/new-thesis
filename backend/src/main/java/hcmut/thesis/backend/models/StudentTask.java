package hcmut.thesis.backend.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "student_task", schema = "thesis", catalog = "")
@IdClass(StudentTaskPK.class)
public class StudentTask {
    private int idTask;
    private int idStudent;
    private String archive;
    private Timestamp uploadDate;

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

    @Basic
    @Column(name = "archive")
    public String getArchive() {
        return archive;
    }

    public void setArchive(String archive) {
        this.archive = archive;
    }

    @Basic
    @Column(name = "upload_date")
    public Timestamp getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Timestamp uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentTask that = (StudentTask) o;
        return idTask == that.idTask &&
                idStudent == that.idStudent &&
                Objects.equals(archive, that.archive) &&
                Objects.equals(uploadDate, that.uploadDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idTask, idStudent, archive, uploadDate);
    }
}
