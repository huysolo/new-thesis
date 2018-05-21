/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.models.ChatGroup;
import hcmut.thesis.backend.models.User;
import hcmut.thesis.backend.modelview.ChatGroupInfo;
import hcmut.thesis.backend.repositories.ChatGroupRepo;
import hcmut.thesis.backend.repositories.StudentRepo;
import hcmut.thesis.backend.repositories.StudentTopicSemRepo;
import hcmut.thesis.backend.repositories.UserRepo;
import hcmut.thesis.backend.services.ChatGroupService;
import hcmut.thesis.backend.services.CommonService;
import hcmut.thesis.backend.services.TaskService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MinBui
 */
@Service
public class ChatGroupServiceImpl implements ChatGroupService {

    @Autowired
    StudentTopicSemRepo stdTopicSemRepo;

    @Autowired
    ChatGroupRepo chatGroupRepo;

    @Autowired
    StudentRepo stdRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    TaskService taskService;

    @Autowired
    CommonService commonService;

    @Override
    public List<ChatGroupInfo> getchatGroupFromStdID(int stdID) {
        List<ChatGroupInfo> listChat = new ArrayList<>();
        try {
            int topicID = taskService.getCurrTopicFromStdID(stdID).getIdTop();
            List<ChatGroup> t = chatGroupRepo.getAllMesageFromtopicID(topicID);
            for (int i = 0; i < t.size(); i++) {
                ChatGroupInfo temp = new ChatGroupInfo();
                User user = userRepo.getUserFromID(t.get(i).getIdUser());
                temp.setUsername(user.getUserName());
                temp.setGender(user.getGender());
                temp.setTopicID(t.get(i).getIdTopicSem());
                temp.setTime(t.get(i).getTime());
                temp.setContent(t.get(i).getContent());
                listChat.add(temp);
            }
            return listChat;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer countMessageBytopicID(int topicIDID) {
        try {
            return chatGroupRepo.countMessageByTopicID(topicIDID);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Integer countMessageByStd(int stdID) {
        int topicID = taskService.getCurrTopicFromStdID(stdID).getIdTop();
        return this.countMessageBytopicID(topicID);
    }

    @Override
    public ChatGroupInfo receiveMessage(String message, Integer stdID, Integer userID, String userName) {
        try {
            ChatGroupInfo msg = new ChatGroupInfo();
            int topicID = taskService.getCurrTopicFromStdID(stdID).getIdTop();
            msg.setContent(message);
            msg.setTime(new Timestamp(System.currentTimeMillis()));
            msg.setUsername(userName);
            msg.setTopicID(topicID);

            ChatGroup cg = new ChatGroup();
            cg.setContent(message);
            cg.setIdTopicSem(topicID);
            cg.setIdUser(userID);
            cg.setTime(new Timestamp(System.currentTimeMillis()));
            chatGroupRepo.save(cg);
            
            return msg;
        } catch (Exception e) {
            return null;
        }
    }
}
