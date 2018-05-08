/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.Meeting;
import hcmut.thesis.backend.models.MeetingSchelule;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author MinBui
 */
public interface MeetingScheduleRepo extends JpaRepository<MeetingSchelule, Integer> {
    @Query("SELECT ms FROM MeetingSchelule ms WHERE ms.idMeeting = :meetingid")
    List<MeetingSchelule> getListMeetingScheduleFromMeetingID(@Param("meetingid") Integer meetingid);
    
    @Query("SELECT ms FROM MeetingSchelule ms WHERE ms.idMeeting = :meetingid AND ms.meetingTime = :meetingTime"
            + " AND ms.location = :location")
    MeetingSchelule getScheduleFromTimeLocationID(@Param("meetingid") Integer meetingid, 
            @Param("meetingTime") Timestamp meetingTime,@Param("location") String location);
}
