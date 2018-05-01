/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.controllers;

import hcmut.thesis.backend.modelview.StudentJoinMeeting;
import hcmut.thesis.backend.modelview.UserSession;
import hcmut.thesis.backend.services.CommonService;
import hcmut.thesis.backend.services.MeetingService;
import hcmut.thesis.backend.services.TaskService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        Integer currSem = commonService.getCurrentSem();
        
        if (currSem != null) {
            Integer topicid = taskService.getCurrTopicFromStdID(stdid).getIdTop();
            return meetingService.getAllStdDoTopic(topicid);
        } else {
            return null;
        }

    }
}
