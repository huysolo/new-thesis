/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.models.StudentTask;
import hcmut.thesis.backend.models.StudentTopicSem;
import hcmut.thesis.backend.models.Task;
import hcmut.thesis.backend.modelview.StudentDoTask;
import hcmut.thesis.backend.modelview.TaskInfo;
import hcmut.thesis.backend.repositories.StudentTaskRepo;
import hcmut.thesis.backend.repositories.StudentTopicSemRepo;
import hcmut.thesis.backend.repositories.TaskRepo;
import hcmut.thesis.backend.services.ITaskDAO;
import hcmut.thesis.backend.services.IUserDAO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MinBui
 */
@Service
public class TaskDAO implements ITaskDAO {
    @Autowired
    TaskRepo taskRepo;
    
    @Autowired
    StudentTaskRepo stdTaskRepo;
    
    @Autowired
    StudentTopicSemRepo stdTopicSemRepo;
    
    @Autowired
    IUserDAO iuserDAO;
    
    @Override
    public void createStudentTask(int taskID, List<StudentDoTask> std){
        for(int i = 0; i< std.size(); i++){
            StudentTask stdTask = new StudentTask();
            stdTask.setIdTask(taskID);
            stdTask.setIdStudent(iuserDAO.getUser(std.get(i).getStdName()).getIdUser());
            stdTaskRepo.save(stdTask);
        }
    }
    
    @Override
    public TaskInfo createTask(TaskInfo taskInfo, int topicid){
        Task newTask = new Task();
        newTask.setTitle(taskInfo.getTitle());
        newTask.setDescription(taskInfo.getDescription());
        newTask.setDeadline(taskInfo.getDeadline());
        newTask.setIdTopicSem(topicid);
        Task task =  taskRepo.saveAndFlush(newTask);
        createStudentTask(task.getIdTask(), taskInfo.getStudent());
        taskInfo.setTaskID(task.getIdTask());
        return taskInfo;
    }
    
    @Override
    public List<StudentDoTask> getStudentDoTask (int topicID){
        List<StudentDoTask> listStd = new ArrayList<StudentDoTask>();
        List<StudentTopicSem> stdTopicSem = stdTopicSemRepo.findAll();
        
        System.out.println(stdTopicSem.size());
        System.out.println(stdTopicSem.get(0).getIdStudent());
        System.out.println(stdTopicSem.get(1).getIdStudent());
        
//        for(int i = 0; i< stdTopicSem.size(); i++){
//            if(stdTopicSem.get(i).getIdTopicSem() == topicID){
//                StudentDoTask temp = new StudentDoTask();
//                System.out.println("userID: "+ stdTopicSem.get(i).getIdStudent() );
//                temp.setStdName(iuserDAO.findUserByUserId(stdTopicSem.get(i).getIdStudent()).getUserName());
//                listStd.add(temp);
//            }
//        }
        return listStd;
    }
}
