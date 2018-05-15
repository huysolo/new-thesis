package hcmut.thesis.backend.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class File {
    private int idFile;
    private String name;
    private Timestamp uploadDate;
    private Integer idUser;
    private Integer idTask;
    private Integer version = 0;

    public File(String name, Integer idUser, Integer idTask, Integer version) {
        this.name = name;
        this.idUser = idUser;
        this.idTask = idTask;
        this.version = version;
    }

    public File() {
    }


    @Id
    @Column(name = "id_file")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdFile() {
        return idFile;
    }

    public void setIdFile(int idFile) {
        this.idFile = idFile;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "upload_date")
    public Timestamp getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Timestamp uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Basic
    @Column(name = "id_user")
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "id_task")
    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return idFile == file.idFile &&
                Objects.equals(name, file.name) &&
                Objects.equals(uploadDate, file.uploadDate) &&
                Objects.equals(idUser, file.idUser) &&
                Objects.equals(idTask, file.idTask);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idFile, name, uploadDate, idUser, idTask);
    }

    @Basic
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }


}
