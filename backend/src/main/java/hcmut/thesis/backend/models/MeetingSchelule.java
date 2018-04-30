package hcmut.thesis.backend.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "meeting_schelule", schema = "thesis", catalog = "")
@IdClass(MeetingSchelulePK.class)
public class MeetingSchelule {
    private Integer status;
    private Timestamp meetingTime;
    private int idMeeting;
    private String location;

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Id
    @Column(name = "meeting_time")
    public Timestamp getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(Timestamp meetingTime) {
        this.meetingTime = meetingTime;
    }

    @Id
    @Column(name = "id_meeting")
    public int getIdMeeting() {
        return idMeeting;
    }

    public void setIdMeeting(int idMeeting) {
        this.idMeeting = idMeeting;
    }

    @Id
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingSchelule that = (MeetingSchelule) o;
        return idMeeting == that.idMeeting &&
                Objects.equals(status, that.status) &&
                Objects.equals(meetingTime, that.meetingTime) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {

        return Objects.hash(status, meetingTime, idMeeting, location);
    }
}
