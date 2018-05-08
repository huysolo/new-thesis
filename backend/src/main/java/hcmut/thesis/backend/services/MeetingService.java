/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.services;

import hcmut.thesis.backend.modelview.MeetingInfo;
import hcmut.thesis.backend.modelview.MeetingTimeLocation;
import hcmut.thesis.backend.modelview.StudentJoinMeeting;
import java.util.List;

/**
 *
 * @author MinBui
 */
public interface MeetingService {
    List<StudentJoinMeeting> getAllStdDoTopic(int topicid);
    MeetingInfo createMeeting(MeetingInfo info);
    List<MeetingInfo> getListMeetingFromTopicID(int topicid);
    MeetingInfo profEditMeeting(MeetingInfo info);
    MeetingInfo stdBookMeeting(MeetingInfo info);
}
