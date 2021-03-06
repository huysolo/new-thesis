/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.controllers;

import com.google.gson.Gson;
import hcmut.thesis.backend.models.JoinPerMeeting;
import hcmut.thesis.backend.models.Meeting;
import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.modelview.MeetingInfo;
import hcmut.thesis.backend.modelview.MeetingReport;
import hcmut.thesis.backend.modelview.MeetingTimeLocation;
import hcmut.thesis.backend.modelview.StudentJoinMeeting;
import hcmut.thesis.backend.modelview.TopicDetail;
import hcmut.thesis.backend.modelview.UserSession;
import hcmut.thesis.backend.repositories.MeetingRepo;
import hcmut.thesis.backend.repositories.TopicRepo;
import hcmut.thesis.backend.services.CommonService;
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
import static javafx.scene.input.KeyCode.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author MinBui
 */
@Controller
@CrossOrigin
@RequestMapping("meeting")
public class MeetingController {

    @Autowired
    MeetingService meetingService;

    @Autowired
    TaskService taskService;

    @Autowired
    UserSession userSession;

    @Autowired
    CommonService commonService;

    @Autowired
    TopicRepo topicRepo;

    @Autowired
    MeetingRepo meetingRepo;
    

    @RequestMapping(value = "/getallstddotopic", method = RequestMethod.GET)
    @ResponseBody
    public List<StudentJoinMeeting> getAllStudentDoTopic(@RequestParam("topicID") Integer topicID) {
        return meetingService.getAllStdDoTopic(topicID);
    }

    @RequestMapping(value = "/createmeeting", method = RequestMethod.POST)
    @ResponseBody
    public MeetingInfo createMeeting(@RequestBody MeetingInfo info) {
        if (userSession.isStudent()) {
            int stdid = userSession.getStudent().getIdStudent();
            try {
                Integer topicid = taskService.getCurrTopicFromStdID(stdid).getIdTop();
                info.setTopicID(topicid);
            } catch (Exception e) {
                return null;
            }
        }
        return meetingService.createMeeting(info);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public MeetingInfo testMeeting(@RequestBody MeetingInfo info) {
        System.out.println(info.getContent());
        System.out.println(info.getTimeLocation().size());
        for (MeetingTimeLocation aT : info.getTimeLocation()) {
            System.out.println(aT.getLocation());
            System.out.println(aT.getMeetingTime());
        }
        return null;
    }
    
    @RequestMapping(value = "/getmeetingfrommeetingid", method = RequestMethod.GET)
    @ResponseBody
    public MeetingInfo getMeetingFromMeetingID(@RequestParam("meetingid") Integer meetingID) {
        try {
            return meetingService.getMeetingFromMeetingID(meetingID);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/getlistrecentmeetingfromtopicid", method = RequestMethod.GET)
    @ResponseBody
    public List<MeetingInfo> getListRecentMeetingFromTopicID(@RequestParam("topicid") Integer topicid) {
        try {
            return meetingService.stdGetListRecentMeeting(topicid);
        } catch (Exception e) {
            return null;
        }
    }
    @RequestMapping(value = "/getlisthistorymeetingfromtopicid", method = RequestMethod.GET)
    @ResponseBody
    public List<MeetingInfo> getListHistoryMeetingFromTopicID(@RequestParam("topicid") Integer topicid) {
        try {
            return meetingService.stdGetListHistoryMeeting(topicid);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/profgetrecentmeeting", method = RequestMethod.GET)
    @ResponseBody
    public List<MeetingInfo> profGetRecentMeeting() {
        int profID = userSession.getProf().getIdProfessor();
        return meetingService.profGetListRecentMeeting(profID);
    }

    @RequestMapping(value = "/profgethistorymeeting", method = RequestMethod.GET)
    @ResponseBody
    public List<MeetingInfo> profGetHistoryMeeting() {
        int profID = userSession.getProf().getIdProfessor();
        return meetingService.profGetListHistoryMeeting(profID);
    }

    @RequestMapping(value = "/stdgetrecentmeeting", method = RequestMethod.GET)
    @ResponseBody
    public List<MeetingInfo> stdGetRecentMeeting() {
        int stdid = userSession.getStudent().getIdStudent();
        try {
            int topicID = taskService.getCurrTopicFromStdID(stdid).getIdTop();
            return meetingService.stdGetListRecentMeeting(topicID);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/stdgethistorymeeting", method = RequestMethod.GET)
    @ResponseBody
    public List<MeetingInfo> stdGetHistoryMeeting() {
        int stdid = userSession.getStudent().getIdStudent();
        try {
            int topicID = taskService.getCurrTopicFromStdID(stdid).getIdTop();
            return meetingService.stdGetListHistoryMeeting(topicID);
        } catch (Exception e) {
            return null;
        }
    }
    
    @RequestMapping(value = "/getmeetingdiary", method = RequestMethod.GET)
    @ResponseBody
    public JoinPerMeeting getMeetingDiaryFromUserID(@RequestParam("meetingID") Integer meetingID) {
        int stdID = userSession.getStudent().getIdStudent();
        return meetingService.getJPMFromMeetingIDStdID(stdID, meetingID);
    }
    
    @RequestMapping(value = "/editmeetingdiary", method = RequestMethod.POST)
    @ResponseBody
    public JoinPerMeeting editMeetingDiary(@RequestBody JoinPerMeeting jpm) {
        return meetingService.editMeetingDiary(jpm);
    }
    
      @RequestMapping(value = "/editmeetingreport", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> editMeeting(@RequestBody MeetingReport report) {
        try{
            if(meetingService.editMeetingReport(report)){
                return ResponseEntity.ok(HttpStatus.OK);
            } else {
                return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
            }
            
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    } 
  
    @RequestMapping(value = "/createschedulemeeting", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> profCreateScheduleMeeting(@RequestBody MeetingInfo info) {
         
        try{
            String status = meetingService.profCreateScheduleMeeting(info);
            if(status == "OK"){
                return ResponseEntity.ok(HttpStatus.OK);
            } else {
                return ResponseEntity.ok(HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/stdbookmeeting", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> stdBookMeeting(@RequestBody MeetingInfo info) { 
        String t = meetingService.stdBookMeeting(info);
        try{
            if(t == "OK"){
                return ResponseEntity.ok(HttpStatus.OK);
            } else {
                return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/cancelmeeting", method = RequestMethod.POST)
    @ResponseBody
    public MeetingInfo cancelMeeting(@RequestBody MeetingInfo info) {
        meetingService.cancelMeeting(info.getMeetingID(), info.getReason());
        System.out.println(info.getMeetingID());
        System.out.println(info.getReason());
        return info;
    }

    @RequestMapping(value = "/gettopicfromid", method = RequestMethod.GET)
    @ResponseBody
    public Topic getTopicTitleFromID(@RequestParam("topicID") Integer topicID) {
        return topicRepo.getTopicFromTopicID(topicID);
    }
    
    @GetMapping("countmeetingbystd")
    public  ResponseEntity<?> countMeetingByStd() {
        int stdID = userSession.getStudent().getIdStudent();
        try{
            return ResponseEntity.ok(meetingService.countMeetingByStd(stdID));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    
    @GetMapping("countmeetingbyprof")
    public  ResponseEntity<?> countMeetingByProf() {
        int profID = userSession.getProf().getIdProfessor();
        try{
            return ResponseEntity.ok(meetingService.countMeetingByProf(profID));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public JoinPerMeeting test(@RequestParam("stdID") Integer stdID, @RequestParam("meetingID") Integer meetingID) {
        return meetingService.getJPMFromMeetingIDStdID(stdID, meetingID);
    }
}
