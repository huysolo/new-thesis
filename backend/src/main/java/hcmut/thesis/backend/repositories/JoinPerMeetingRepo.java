/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.JoinPerMeeting;
import hcmut.thesis.backend.models.MeetingSchelule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author MinBui
 */

public interface JoinPerMeetingRepo extends JpaRepository<JoinPerMeeting, Integer> {
    @Query("SELECT jpm FROM JoinPerMeeting jpm WHERE jpm.idMeeting = :meetingid")
    List<JoinPerMeeting> getListStudentFromMeetingID(@Param("meetingid") Integer meetingid);
    
    @Query("SELECT jpm FROM JoinPerMeeting jpm WHERE jpm.idMeeting = :meetingid AND jpm.idStudent = :stdid")
    JoinPerMeeting getJPMFromMeetingIDStdID(@Param("meetingid") Integer meetingid, @Param("stdid") Integer stdid);
}
