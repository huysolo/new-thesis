/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.services;

import hcmut.thesis.backend.models.JoinPerMeeting;
import hcmut.thesis.backend.modelview.MeetingInfo;
import hcmut.thesis.backend.modelview.MeetingReport;
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
    String profCreateScheduleMeeting(MeetingInfo info);
    String stdBookMeeting(MeetingInfo info);
    void cancelMeeting (int meetingid, String reason);
    List<MeetingInfo> profGetListRecentMeeting(int profID);
    List<MeetingInfo> profGetListHistoryMeeting(int profID);
    List<MeetingInfo> stdGetListRecentMeeting(int topicID);
    List<MeetingInfo> stdGetListHistoryMeeting(int topicID);
    JoinPerMeeting getJPMFromMeetingIDStdID(int stdID, int meetingID);
    JoinPerMeeting editMeetingDiary(JoinPerMeeting jpm);
    Boolean editMeetingReport(MeetingReport report);
    MeetingInfo getMeetingFromMeetingID(int meetingID);
    Integer countTaskByTopicID(Integer topicID);
    Integer countMeetingByStd(Integer stdID);
    Integer countMeetingByProf(Integer profID);
}
