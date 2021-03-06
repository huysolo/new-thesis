/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.controllers.MeetingController;
import hcmut.thesis.backend.models.JoinPerMeeting;
import hcmut.thesis.backend.models.Meeting;
import hcmut.thesis.backend.models.MeetingSchelule;
import hcmut.thesis.backend.models.StudentTopicSem;
import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.modelview.MeetingInfo;
import hcmut.thesis.backend.modelview.MeetingReport;
import hcmut.thesis.backend.modelview.MeetingTimeLocation;
import hcmut.thesis.backend.modelview.StudentDoTask;
import hcmut.thesis.backend.modelview.StudentJoinMeeting;
import hcmut.thesis.backend.repositories.JoinPerMeetingRepo;
import hcmut.thesis.backend.repositories.MeetingRepo;
import hcmut.thesis.backend.repositories.MeetingScheduleRepo;
import hcmut.thesis.backend.repositories.StudentRepo;
import hcmut.thesis.backend.repositories.StudentTopicSemRepo;
import hcmut.thesis.backend.repositories.TopicRepo;
import hcmut.thesis.backend.repositories.UserRepo;
import hcmut.thesis.backend.services.CommonService;
import hcmut.thesis.backend.services.IUserDAO;
import hcmut.thesis.backend.services.MeetingService;
import hcmut.thesis.backend.services.TaskService;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MinBui
 */
@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    StudentTopicSemRepo stdTopicSemRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    StudentRepo stdRepo;

    @Autowired
    MeetingRepo meetingRepo;

    @Autowired
    JoinPerMeetingRepo joinMeetingRepo;

    @Autowired
    MeetingScheduleRepo meetingScheduleRepo;

    @Autowired
    IUserDAO iuserDAO;

    @Autowired
    CommonService commonService;

    @Autowired
    TaskService taskService;

    @Autowired
    TopicRepo topicRepo;

    @Override
    public List<StudentJoinMeeting> getAllStdDoTopic(int topicid) {
        List<StudentJoinMeeting> listStd = new ArrayList<>();
        List<StudentTopicSem> t = stdTopicSemRepo.getAllStudentByIdTopicSem(topicid);

        for (int i = 0; i < t.size(); i++) {
            StudentJoinMeeting temp = new StudentJoinMeeting();
            int stdID = t.get(i).getIdStudent();
            int userID = stdRepo.getUserIDFromStdID(stdID);
            temp.setName(userRepo.getUserNameFromID(userID));
            temp.setStdID(stdID);
            listStd.add(temp);
        }

        return listStd;
    }

    @Override
    public MeetingInfo createMeeting(MeetingInfo info) {
        Meeting meeting = new Meeting();

        meeting.setTitle(info.getTitle());
        meeting.setContent(info.getContent());
        meeting.setReason(info.getReason());
        meeting.setIdTopicSem(info.getTopicID());
        meeting.setStatus(0);
        info.setStatus(0);
        meeting.setStudentCount(info.getStudent().size());

        Meeting temp = meetingRepo.saveAndFlush(meeting);
        info.setMeetingID(temp.getIdMeeting());

        if (info.getTimeLocation().size() > 0 && info.getTimeLocation().get(0).getLocation() != null) {
            for (MeetingTimeLocation aT : info.getTimeLocation()) {
                MeetingSchelule schedule = new MeetingSchelule();
                schedule.setIdMeeting(temp.getIdMeeting());
                schedule.setLocation(aT.getLocation());
                schedule.setMeetingTime(aT.getMeetingTime());
                schedule.setStatus(0);
                aT.setStatus(0);
                meetingScheduleRepo.save(schedule);
            }
        }

        return info;
    }

    MeetingInfo mappingMeetingToMeetingInfo(Meeting meeting) {
        MeetingInfo temp = new MeetingInfo();
        temp.setTopicID(meeting.getIdTopicSem());
        temp.setMeetingID(meeting.getIdMeeting());
        temp.setStatus(meeting.getStatus());
        temp.setTitle(meeting.getTitle());
        temp.setContent(meeting.getContent());
        temp.setReason(meeting.getReason());
        temp.setReportContent(meeting.getReportContent());
        temp.setReportPlan(meeting.getReportPlan());
        temp.setStudent(mappingToStdJoinMeeting(meeting.getIdMeeting()));
        temp.setTimeLocation(mappingFromScheduleToTimeLocation(meeting.getIdMeeting()));

        return temp;
    }

    @Override
    public List<MeetingInfo> getListMeetingFromTopicID(int topicid) {
        List<MeetingInfo> list = new ArrayList<>();
        List<Meeting> listMeeting = meetingRepo.getListMeetingFromTopicID(topicid);
        for (Meeting meeting : listMeeting) {
            MeetingInfo temp = this.mappingMeetingToMeetingInfo(meeting);
            list.add(temp);
        }
        return list;
    }

    @Override
    public MeetingInfo getMeetingFromMeetingID(int meetingID) {
        Meeting meeting = meetingRepo.getMeetingFromMeetingID(meetingID);
        MeetingInfo temp = this.mappingMeetingToMeetingInfo(meeting);
        return temp;
    }

    List<MeetingTimeLocation> mappingFromScheduleToTimeLocation(int meetingid) {
        List<MeetingTimeLocation> listTimeLocation = new ArrayList<>();
        List<MeetingSchelule> listSchedule = meetingScheduleRepo.getListMeetingScheduleFromMeetingID(meetingid);
        try {
            for (MeetingSchelule aT : listSchedule) {
                MeetingTimeLocation temp = new MeetingTimeLocation();
                temp.setLocation(aT.getLocation());
                temp.setMeetingTime(aT.getMeetingTime());
                temp.setStatus(aT.getStatus());
                listTimeLocation.add(temp);
            }
            return listTimeLocation;
        } catch (Exception e) {
            return null;
        }
    }

    List<StudentJoinMeeting> mappingToStdJoinMeeting(int meetingid) {
        List<StudentJoinMeeting> listStudent = new ArrayList<>();
        List<JoinPerMeeting> listJPM = joinMeetingRepo.getListStudentFromMeetingID(meetingid);
        try {
            for (JoinPerMeeting aT : listJPM) {
                StudentJoinMeeting temp = new StudentJoinMeeting();
                temp.setMeetingID(meetingid);
                temp.setStdID(aT.getIdStudent());
                int userID = stdRepo.getUserIDFromStdID(aT.getIdStudent());
                String usrName = userRepo.getUserNameFromID(userID);
                temp.setName(usrName);
                listStudent.add(temp);
            }
            return listStudent;
        } catch (Exception e) {
            return null;
        }
    }
    
    void deleteTimeLocationFromMeetingID(Integer meetingID){
        List<MeetingSchelule> listSchedule = meetingScheduleRepo.getListMeetingScheduleFromMeetingID(meetingID);
        if(listSchedule.size() > 0){
            for(MeetingSchelule temp: listSchedule){
                meetingScheduleRepo.delete(temp);
            }
        }
        
    }

    @Override
    public String profCreateScheduleMeeting(MeetingInfo info) {
        Meeting meeting = meetingRepo.getMeetingFromMeetingID(info.getMeetingID());
        if (meeting.getStatus() == 1) {
            return "CONFLICT";
        } else {
            if (info.getTimeLocation().size() > 0 && info.getTimeLocation().get(0).getLocation() != null) {
                this.deleteTimeLocationFromMeetingID(info.getMeetingID());
                for (MeetingTimeLocation aT : info.getTimeLocation()) {
                    MeetingSchelule schedule = new MeetingSchelule();
                    schedule.setIdMeeting(info.getMeetingID());
                    schedule.setLocation(aT.getLocation());
                    schedule.setMeetingTime(aT.getMeetingTime());
                    schedule.setStatus(0);
                    meetingScheduleRepo.save(schedule);
                }
                return "OK";
            } else {
                return null;
            }
        }

    }

    @Override
    public String stdBookMeeting(MeetingInfo info) {
        if (info.getStatus() == 0) {
            if (info.getStudent().size() > 0) {
                for (StudentJoinMeeting aT : info.getStudent()) {
                    JoinPerMeeting tem = new JoinPerMeeting();
                    tem.setIdMeeting(info.getMeetingID());
                    tem.setIdStudent(aT.getStdID());
                    joinMeetingRepo.save(tem);
                }

            } else {

            }

            if (info.getTimeLocation().size() > 0 && info.getTimeLocation().get(0).getLocation() != null) {
                for (MeetingTimeLocation aT : info.getTimeLocation()) {
                    if (aT.getStatus() == 1) {
                        MeetingSchelule temp = meetingScheduleRepo.getScheduleFromTimeLocationID(
                                info.getMeetingID(), aT.getMeetingTime(), aT.getLocation());
                        temp.setStatus(1);
                        meetingScheduleRepo.save(temp);
                    }
                }
            }

            info.setStatus(1);

            Meeting temp = meetingRepo.getMeetingFromMeetingID(info.getMeetingID());
            temp.setStatus(1);
            meetingRepo.save(temp);
            return "OK";
        } else {
            if (info.getTimeLocation().size() > 0) {
                deleteStudentJoinMeeting(info.getMeetingID());
                for (StudentJoinMeeting aT : info.getStudent()) {
                    JoinPerMeeting tem = new JoinPerMeeting();
                    tem.setIdMeeting(info.getMeetingID());
                    tem.setIdStudent(aT.getStdID());
                    joinMeetingRepo.save(tem);
                }
            }
            return "OK";
        }

    }

    void deleteStudentJoinMeeting(Integer meetingID) {
        List<JoinPerMeeting> liststd = joinMeetingRepo.getListStudentFromMeetingID(meetingID);
        for (JoinPerMeeting temp : liststd) {
            joinMeetingRepo.delete(temp);
        }
    }

    @Override
    public void cancelMeeting(int meetingid, String reason) {
        Meeting temp = meetingRepo.getMeetingFromMeetingID(meetingid);
        temp.setReason(reason);
        temp.setStatus(2);
        meetingRepo.save(temp);
    }

    @Override
    public List<MeetingInfo> profGetListRecentMeeting(int profID) {
        List<MeetingInfo> listStudent = new ArrayList<>();
        try {
            int currSem = commonService.getCurrentSem();
            List<Topic> listTopic = topicRepo.findListTopicFromSemID(profID, currSem);
            for (Topic aT : listTopic) {
                List<Meeting> listWaiting = meetingRepo.getListWaitingMeeting(aT.getIdTop());
                List<Meeting> listBooked = new ArrayList<>();
                List<Meeting> tempBooked = meetingRepo.getListBookedMeeting(aT.getIdTop());

                for (Meeting tempBook : tempBooked) {
                    try {
                        if (meetingScheduleRepo.getRecentSchedule(tempBook.getIdMeeting()).getStatus() == 1) {
                            listBooked.add(tempBook);
                        }
                    } catch (Exception e) {

                    }
                }

                for (Meeting waitingMeeting : listWaiting) {
                    MeetingInfo waitingTemp = this.mappingMeetingToMeetingInfo(waitingMeeting);
                    listStudent.add(waitingTemp);
                }
                for (Meeting meeting : listBooked) {
                    MeetingInfo temp = this.mappingMeetingToMeetingInfo(meeting);
                    listStudent.add(temp);
                }
            }
        } catch (Exception e) {

        }
        return listStudent;
    }

    @Override
    public List<MeetingInfo> profGetListHistoryMeeting(int profID) {
        List<MeetingInfo> listStudent = new ArrayList<>();
        try {
            int currSem = commonService.getCurrentSem();
            List<Topic> listTopic = topicRepo.findListTopicFromSemID(profID, currSem);
            for (Topic aT : listTopic) {
                List<Meeting> listBooked = new ArrayList<>();
                List<Meeting> tempBooked = meetingRepo.getListBookedMeeting(aT.getIdTop());

                for (Meeting tempBook : tempBooked) {
                    try {
                        if (meetingScheduleRepo.getHistorySchedule(tempBook.getIdMeeting()).getStatus() == 1) {
                            listBooked.add(tempBook);
                        }
                    } catch (Exception e) {

                    }
                }

                for (Meeting meeting : listBooked) {
                    MeetingInfo temp = this.mappingMeetingToMeetingInfo(meeting);
                    listStudent.add(temp);
                }
            }
        } catch (Exception e) {

        }
        return listStudent;
    }

    @Override
    public List<MeetingInfo> stdGetListHistoryMeeting(int topicID) {
        List<MeetingInfo> listStudent = new ArrayList<>();
        List<Meeting> listBooked = new ArrayList<>();
        List<Meeting> tempBooked = meetingRepo.getListBookedMeeting(topicID);

        for (Meeting tempBook : tempBooked) {
            try {
                if (meetingScheduleRepo.getHistorySchedule(tempBook.getIdMeeting()).getStatus() == 1) {
                    listBooked.add(tempBook);
                }
            } catch (Exception e) {

            }
        }

        for (Meeting meeting : listBooked) {
            MeetingInfo temp = this.mappingMeetingToMeetingInfo(meeting);
            listStudent.add(temp);
        }
        return listStudent;
    }

    @Override
    public List<MeetingInfo> stdGetListRecentMeeting(int topicID) {
        List<MeetingInfo> listStudent = new ArrayList<>();
        List<Meeting> listWaiting = meetingRepo.getListWaitingMeeting(topicID);
        List<Meeting> listBooked = new ArrayList<>();
        List<Meeting> tempBooked = meetingRepo.getListBookedMeeting(topicID);

        for (Meeting tempBook : tempBooked) {
            try {
                if (meetingScheduleRepo.getRecentSchedule(tempBook.getIdMeeting()).getStatus() == 1) {
                    listBooked.add(tempBook);
                }
            } catch (Exception e) {

            }
        }

        for (Meeting waitingMeeting : listWaiting) {
            MeetingInfo waitingTemp = this.mappingMeetingToMeetingInfo(waitingMeeting);
            listStudent.add(waitingTemp);
        }

        for (Meeting meeting : listBooked) {
            MeetingInfo temp = this.mappingMeetingToMeetingInfo(meeting);
            listStudent.add(temp);
        }
        return listStudent;
    }

    @Override
    public JoinPerMeeting getJPMFromMeetingIDStdID(int stdID, int meetingID) {
        try {
            return joinMeetingRepo.getJPMFromMeetingIDStdID(meetingID, stdID);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public JoinPerMeeting editMeetingDiary(JoinPerMeeting jpm) {
        joinMeetingRepo.save(jpm);
        return jpm;
    }

    @Override
    public Integer countTaskByTopicID(Integer topicID) {
        try {
            return meetingRepo.countTaskByTopicID(topicID);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Integer countMeetingByStd(Integer stdID) {
        int topicID = taskService.getCurrTopicFromStdID(stdID).getIdTop();
        return this.countTaskByTopicID(topicID);
    }

    @Override
    public Integer countMeetingByProf(Integer profID) {
        Integer meetingCount = 0;
        try {
            int currSem = commonService.getCurrentSem();
            List<Topic> listTopic = topicRepo.findListTopicFromSemID(profID, currSem);
            for (Topic topic : listTopic) {
                meetingCount = meetingCount + this.countTaskByTopicID(topic.getIdTop());
            }
            return meetingCount;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Boolean editMeetingReport(MeetingReport report) {
        try {
            Meeting meeting = meetingRepo.getMeetingFromMeetingID(report.getMeetingID());
            meeting.setReportContent(report.getContent());
            meeting.setReportPlan(report.getPlan());
            meetingRepo.save(meeting);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
