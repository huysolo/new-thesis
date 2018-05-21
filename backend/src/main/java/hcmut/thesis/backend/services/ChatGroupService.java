/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.services;

import hcmut.thesis.backend.modelview.ChatGroupInfo;
import java.util.List;

/**
 *
 * @author MinBui
 */
public interface ChatGroupService {
    List<ChatGroupInfo> getchatGroupFromStdID(int stdID);
    Integer countMessageBytopicID(int topicIDID);
    Integer countMessageByStd(int stdID);
    ChatGroupInfo receiveMessage(String message, Integer stdID, Integer userID, String userName);
}
