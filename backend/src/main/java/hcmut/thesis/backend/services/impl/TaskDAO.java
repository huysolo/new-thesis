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
import hcmut.thesis.backend.repositories.StudentRepo;
import hcmut.thesis.backend.repositories.StudentTaskRepo;
import hcmut.thesis.backend.repositories.StudentTopicSemRepo;
import hcmut.thesis.backend.repositories.TaskRepo;
import hcmut.thesis.backend.services.ITaskDAO;
import hcmut.thesis.backend.services.IUserDAO;
import java.util.ArrayList;
import java.util.LinkedList;
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
    
    @Autowired
    StudentRepo stdRepo;
    
    @Override
    public void createStudentTask(int taskID, List<Integer> studentIdList){
        List<StudentTask> studentTaskList = new LinkedList<>();
        studentIdList.forEach(id-> studentTaskList.add(new StudentTask(taskID, id)));
        stdTaskRepo.saveAll(studentTaskList);
    }
    
    @Override
    public Task createTask(TaskInfo taskInfo, int topicid){
        Task newTask = new Task();
        newTask.setTitle(taskInfo.getTitle());
        newTask.setDescription(taskInfo.getDescription());
        newTask.setDeadline(taskInfo.getDeadline());
        newTask.setIdTopicSem(topicid);
        Task task =  taskRepo.save(newTask);
        createStudentTask(task.getIdTask(), taskInfo.getStudentIdList());
        taskInfo.setTaskID(task.getIdTask());
        return task;
    }
    
    @Override
    public List<StudentDoTask> getStudentDoTask (int topicID){
        List<StudentDoTask> listStd = new ArrayList<>();
        List<StudentTopicSem> stdTopicSem = stdTopicSemRepo.findAll();
        
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
