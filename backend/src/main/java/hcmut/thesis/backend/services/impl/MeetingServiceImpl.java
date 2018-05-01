/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.models.StudentTopicSem;
import hcmut.thesis.backend.modelview.StudentDoTask;
import hcmut.thesis.backend.modelview.StudentJoinMeeting;
import hcmut.thesis.backend.repositories.StudentRepo;
import hcmut.thesis.backend.repositories.StudentTopicSemRepo;
import hcmut.thesis.backend.repositories.UserRepo;
import hcmut.thesis.backend.services.MeetingService;
import java.util.ArrayList;
import java.util.List;
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
    
    @Override
    public List<StudentJoinMeeting> getAllStdDoTopic(int topicid){
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
}
