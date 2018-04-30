package hcmut.thesis.backend.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class JoinPerMeetingPK implements Serializable {
    private int idStudent;
    private int idMeeting;

    @Column(name = "id_student")
    @Id
    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    @Column(name = "id_meeting")
    @Id
    public int getIdMeeting() {
        return idMeeting;
    }

    public void setIdMeeting(int idMeeting) {
        this.idMeeting = idMeeting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JoinPerMeetingPK that = (JoinPerMeetingPK) o;
        return idStudent == that.idStudent &&
                idMeeting == that.idMeeting;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idStudent, idMeeting);
    }
}
