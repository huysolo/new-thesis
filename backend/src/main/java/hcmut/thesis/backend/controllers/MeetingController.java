/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.controllers;

import com.google.gson.Gson;
import hcmut.thesis.backend.modelview.MeetingInfo;
import hcmut.thesis.backend.modelview.MeetingTimeLocation;
import hcmut.thesis.backend.modelview.StudentJoinMeeting;
import hcmut.thesis.backend.modelview.TopicDetail;
import hcmut.thesis.backend.modelview.UserSession;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @RequestMapping(value = "/getallstddotopic", method = RequestMethod.GET)
    @ResponseBody
    public List<StudentJoinMeeting> getAllStudentDoTopic() {

        int stdid = userSession.getStudent().getIdStudent();

        try {
            Integer topicid = taskService.getCurrTopicFromStdID(stdid).getIdTop();
            return meetingService.getAllStdDoTopic(topicid);
        } catch (Exception e) {
            return null;
        }

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
          for(MeetingTimeLocation aT: info.getTimeLocation()){
              System.out.println(aT.getLocation());
              System.out.println(aT.getMeetingTime());
          }
        return null;
    }

    @RequestMapping(value = "/getlistmeeting", method = RequestMethod.GET)
    @ResponseBody
    public List<MeetingInfo> getListMeetingFromTopicID(@RequestParam("topicid") Integer topicid) {
         if (topicid == -1) {
            int stdid = userSession.getStudent().getIdStudent();

            try {
                topicid = taskService.getCurrTopicFromStdID(stdid).getIdTop();
            } catch (Exception e) {
                return null;
            }
        }
        
        return meetingService.getListMeetingFromTopicID(topicid);
    }

    @RequestMapping(value = "/editmeeting", method = RequestMethod.POST)
    @ResponseBody
    public MeetingInfo editMeeting(@RequestBody MeetingInfo info) {
       if(userSession.isProf()){
           return meetingService.profEditMeeting(info);
       } else {
           return meetingService.stdBookMeeting(info);
       }
    }
    
    @RequestMapping(value = "/cancelmeeting", method = RequestMethod.POST)
    @ResponseBody
    public MeetingInfo cancelMeeting(@RequestBody MeetingInfo info) {
        meetingService.cancelMeeting(info.getMeetingID(), info.getReason());
        return info;
    }
}
