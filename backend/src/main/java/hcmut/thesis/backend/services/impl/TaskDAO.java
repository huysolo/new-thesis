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

import java.sql.Timestamp;
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
    
    @Override
    public void createStudentTask(int taskID, List<Integer> studentIdList){
        List<StudentTask> studentTaskList = new LinkedList<>();
        studentIdList.forEach(id-> studentTaskList.add(new StudentTask(taskID, id)));
        stdTaskRepo.saveAll(studentTaskList);
    }
    
    @Override
    public Task createTask(TaskInfo taskInfo, int topicId){
        Task newTask = new Task();
        newTask.setTitle(taskInfo.getTitle());
        newTask.setDescription(taskInfo.getDescription());
        newTask.setDeadline(taskInfo.getDeadline());
        newTask.setIdTopicSem(topicId);
        newTask.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        Task task =  taskRepo.save(newTask);
        createStudentTask(task.getIdTask(), taskInfo.getStudentIdList());
        return task;
    }

}
