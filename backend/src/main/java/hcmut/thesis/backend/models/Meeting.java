package hcmut.thesis.backend.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Meeting {
    private int idMeeting;
    private Integer status;
    private String content;
    private Integer studentCount;
    private Integer idTopicSem;
    private String reason;
    private String title;

    @Id
    @Column(name = "id_meeting")
    public int getIdMeeting() {
        return idMeeting;
    }

    public void setIdMeeting(int idMeeting) {
        this.idMeeting = idMeeting;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
    @Column(name = "student_count")
    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    @Basic
    @Column(name = "id_topic_sem")
    public Integer getIdTopicSem() {
        return idTopicSem;
    }

    public void setIdTopicSem(Integer idTopicSem) {
        this.idTopicSem = idTopicSem;
    }

    @Basic
    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return idMeeting == meeting.idMeeting &&
                Objects.equals(status, meeting.status) &&
                Objects.equals(content, meeting.content) &&
                Objects.equals(studentCount, meeting.studentCount) &&
                Objects.equals(idTopicSem, meeting.idTopicSem) &&
                Objects.equals(reason, meeting.reason) &&
                Objects.equals(title, meeting.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idMeeting, status, content, studentCount, idTopicSem, reason, title);
    }
}
