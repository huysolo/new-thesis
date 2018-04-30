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
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MinBui
 */
@Service
public class ChatGroupServiceImpl implements ChatGroupService{
    @Autowired
    StudentTopicSemRepo stdTopicSemRepo;
    
    @Autowired
    ChatGroupRepo chatGroupRepo;
    
    @Autowired
    StudentRepo stdRepo;
    
    @Autowired
    UserRepo userRepo;
    
    @Override
    public List<ChatGroupInfo> getchatGroupFromUderID(int userID){
        List<ChatGroupInfo> listChat = new ArrayList<>();
        List<ChatGroup> t = chatGroupRepo.getAllMesageFromtopicID(stdTopicSemRepo.getTopicIDFromStudentID(stdRepo.getStdIDFromUserID(userID)));
        for(int i = 0; i< t.size(); i++){
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
    }
}
