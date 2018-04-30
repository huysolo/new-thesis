package hcmut.thesis.backend.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student_topic_sem", schema = "thesis", catalog = "")
@IdClass(StudentTopicSemPK.class)
public class StudentTopicSem {
    private int idStudent;
    private int idTopicSem;
    private int teamLead;

    @Id
    @Basic
    @Column(name = "id_student")
    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    @Id
    @Column(name = "id_topic_sem")
    public int getIdTopicSem() {
        return idTopicSem;
    }

    public void setIdTopicSem(int idTopicSem) {
        this.idTopicSem = idTopicSem;
    }

    @Basic
    @Column(name = "team_lead")
    public int getTeamLead() {
        return teamLead;
    }

    public void setTeamLead(int teamLead) {
        this.teamLead = teamLead;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentTopicSem that = (StudentTopicSem) o;
        return idStudent == that.idStudent &&
                idTopicSem == that.idTopicSem &&
                teamLead == that.teamLead;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idStudent, idTopicSem, teamLead);
    }
}
