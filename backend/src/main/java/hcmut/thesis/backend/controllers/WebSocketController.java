package hcmut.thesis.backend.controllers;

import hcmut.thesis.backend.models.ChatGroup;
import hcmut.thesis.backend.models.CommentTask;
import hcmut.thesis.backend.models.User;
import hcmut.thesis.backend.modelview.ChatGroupInfo;
import hcmut.thesis.backend.modelview.TaskComment;
import hcmut.thesis.backend.modelview.UserSession;
import hcmut.thesis.backend.repositories.ChatGroupRepo;
import hcmut.thesis.backend.repositories.StudentTopicSemRepo;
import hcmut.thesis.backend.repositories.TaskCommentRepo;
import hcmut.thesis.backend.repositories.UserRepo;
import hcmut.thesis.backend.services.ChatGroupService;
import hcmut.thesis.backend.services.CommonService;
import hcmut.thesis.backend.services.TaskService;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate template;
    
    @Autowired
    private UserSession userSession;
    
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private StudentTopicSemRepo stdTopicSemRepo;
    
    @Autowired
    private ChatGroupRepo chatGroupRepo;
    
    @Autowired
    private TaskCommentRepo taskCommentRepo;
    
    @Autowired
    private ChatGroupService chatGroupService;
    
    @Autowired
    private TaskService taskService;


     @RequestMapping(value = "/notify", method = RequestMethod.GET)
    public void getReceiveMessage(@RequestParam("message") String message) {
        Integer stdID = userSession.getStudent().getIdStudent();
        Integer userID = userSession.getUserID();
        String userName = userSession.getUser().getUserName();
        ChatGroupInfo msg = chatGroupService.receiveMessage(message, stdID, userID, userName);
        
        try{
            Integer topicID = msg.getTopicID();
            msg.setTopicID(0);
            template.convertAndSend("/topic/notification"+ topicID , msg);
        } catch(Exception e){
        
        }

    }
    
    @RequestMapping(value="/taskcomment", method = RequestMethod.GET)
    public String getComment(@RequestParam("comment") String comment,
                             @RequestParam("taskid") int taskid){
        
        TaskComment cmt = new TaskComment();
        cmt.setContent(comment);
        cmt.setTime(new Timestamp(System.currentTimeMillis()));
        cmt.setTaskID(taskid);
        User user = userRepo.getUserFromID(userSession.getUserID());
        cmt.setUsername(user.getUserName());
        cmt.setGender(user.getGender());
        
        CommentTask temp = new CommentTask();
        temp.setIdTask(taskid);
        temp.setContent(comment);
        temp.setIdUser(userSession.getUserID());
        temp.setTime(new Timestamp(System.currentTimeMillis()));
        taskCommentRepo.save(temp);
        
        template.convertAndSend("/topic/comment" +taskid ,cmt);

        return null;
    }
}
