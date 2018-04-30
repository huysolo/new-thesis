package hcmut.thesis.backend.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Notification {
    private int idNotify;
    private int idUser;
    private Timestamp createDate;
    private String content;
    private String notifyType;

    @Id
    @Column(name = "id_notify")
    public int getIdNotify() {
        return idNotify;
    }

    public void setIdNotify(int idNotify) {
        this.idNotify = idNotify;
    }

    @Basic
    @Column(name = "id_user")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "notify_type")
    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return idNotify == that.idNotify &&
                idUser == that.idUser &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(content, that.content) &&
                Objects.equals(notifyType, that.notifyType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idNotify, idUser, createDate, content, notifyType);
    }
}
