/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.Meeting;
import hcmut.thesis.backend.models.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author MinBui
 */
public interface MeetingRepo extends JpaRepository<Meeting, Integer> {
    @Query("SELECT m FROM Meeting m WHERE m.idTopicSem = :topicid")
    List<Meeting> getListMeetingFromTopicID(@Param("topicid") Integer topicid);
    
    @Query("SELECT m FROM Meeting m WHERE m.idMeeting = :id")
    Meeting getMeetingFromMeetingID(@Param("id") Integer meetingID);
    
    @Query("SELECT m FROM Meeting m WHERE m.idTopicSem = :topicid AND m.status = 0")
    List<Meeting> getListWaitingMeeting(@Param("topicid") Integer topicid);    
    
    @Query("SELECT m FROM Meeting m WHERE m.idTopicSem = :topicid AND (m.status = 1 OR m.status = 2)")
    List<Meeting> getListBookedMeeting(@Param("topicid") Integer topicid);
    
    
}
