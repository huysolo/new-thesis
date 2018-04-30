package hcmut.thesis.backend.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class MeetingSchelulePK implements Serializable {
    private Timestamp meetingTime;
    private int idMeeting;
    private String location;

    @Column(name = "meeting_time")
    @Id
    public Timestamp getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(Timestamp meetingTime) {
        this.meetingTime = meetingTime;
    }

    @Column(name = "id_meeting")
    @Id
    public int getIdMeeting() {
        return idMeeting;
    }

    public void setIdMeeting(int idMeeting) {
        this.idMeeting = idMeeting;
    }

    @Column(name = "location")
    @Id
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
        MeetingSchelulePK that = (MeetingSchelulePK) o;
        return idMeeting == that.idMeeting &&
                Objects.equals(meetingTime, that.meetingTime) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {

        return Objects.hash(meetingTime, idMeeting, location);
    }
}
