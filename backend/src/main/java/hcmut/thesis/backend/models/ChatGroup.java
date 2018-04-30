package hcmut.thesis.backend.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "chat_group", schema = "thesis", catalog = "")
@IdClass(ChatGroupPK.class)
public class ChatGroup {
    private Timestamp time;
    private int idTopicSem;
    private int idUser;
    private String content;

    @Id
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "id_topic_sem")
    public int getIdTopicSem() {
        return idTopicSem;
    }

    public void setIdTopicSem(int idTopicSem) {
        this.idTopicSem = idTopicSem;
    }

    @Id
    @Column(name = "id_user")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatGroup chatGroup = (ChatGroup) o;
        return idTopicSem == chatGroup.idTopicSem &&
                idUser == chatGroup.idUser &&
                Objects.equals(time, chatGroup.time) &&
                Objects.equals(content, chatGroup.content);
    }

    @Override
    public int hashCode() {

        return Objects.hash(time, idTopicSem, idUser, content);
    }
}
