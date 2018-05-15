package hcmut.thesis.backend.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "join_per_meeting", schema = "thesis", catalog = "")
@IdClass(JoinPerMeetingPK.class)
public class JoinPerMeeting {
    private int idStudent;
    private int idMeeting;
    private String diaryContent;
    private String diaryPlan;

    @Id
    @Column(name = "id_student")
    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    @Id
    @Column(name = "id_meeting")
    public int getIdMeeting() {
        return idMeeting;
    }

    public void setIdMeeting(int idMeeting) {
        this.idMeeting = idMeeting;
    }
    
    @Basic
    @Column(name = "diary_content")
    public String getDiaryContent() {
        return this.diaryContent;
    }

    public void setDiaryContent(String content) {
        this.diaryContent= content;
    }
    
    @Basic
    @Column(name = "diary_plan")
    public String getDiaryPlan() {
        return this.diaryPlan;
    }

    public void setDiaryPlan(String plan) {
        this.diaryPlan = plan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JoinPerMeeting that = (JoinPerMeeting) o;
        return idStudent == that.idStudent &&
                idMeeting == that.idMeeting &&
                Objects.equals(diaryContent, that.diaryContent) &&
                Objects.equals(diaryPlan, that.diaryPlan) ;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idStudent, idMeeting,diaryContent, diaryPlan);
    }
}
