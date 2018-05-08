/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.models.*;
import hcmut.thesis.backend.modelview.PageInfo;
import hcmut.thesis.backend.modelview.StudentDoTask;
import hcmut.thesis.backend.modelview.TaskComment;
import hcmut.thesis.backend.modelview.TaskInfo;
import hcmut.thesis.backend.repositories.SemesterRepo;
import hcmut.thesis.backend.repositories.StudentRepo;
import hcmut.thesis.backend.repositories.StudentTaskRepo;
import hcmut.thesis.backend.repositories.StudentTopicSemRepo;
import hcmut.thesis.backend.repositories.TaskCommentRepo;
import hcmut.thesis.backend.repositories.TaskRepo;
import hcmut.thesis.backend.repositories.TopicRepo;
import hcmut.thesis.backend.repositories.UserRepo;
import hcmut.thesis.backend.services.CommonService;
import hcmut.thesis.backend.repositories.*;
import hcmut.thesis.backend.services.TaskService;
import hcmut.thesis.backend.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.min;

/**
 *
 * @author MinBui
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    StudentTaskRepo stdTaskRepo;

    @Autowired
    TaskRepo taskRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    StudentTopicSemRepo stdTopicSemRepo;

    @Autowired
    TaskCommentRepo taskCommentRepo;

    @Autowired
    TopicService topicService;

    @Autowired
    TopicRepo topicRepo;

    @Autowired
    SemesterRepo semRepo;


    @Autowired
    CommonService commonService;
    
    @Autowired
    StudentRepo stdRepo;


    @Autowired
    FileRepo fileRepo;

    @Override
    public List<StudentDoTask> getStudentDoTaskFromTaskID(int taskID) {
        List<StudentDoTask> listStd = new ArrayList<>();
        List<StudentTask> t = stdTaskRepo.getStudentDoTaskFromIDTask(taskID);
        for (StudentTask aT : t) {
            StudentDoTask temp = new StudentDoTask();
            temp.setStdName(userRepo.getUserNameFromID(aT.getIdStudent()));
            temp.setArchive(aT.getArchive());
            temp.setUploadDate(aT.getUploadDate());
            listStd.add(temp);
        }
        return listStd;
    }
        

    @Override
    public List<TaskInfo> getListTaskFromIDTopic(int topicID) {
        List<TaskInfo> listTask = new ArrayList<>();
        List<Task> t = taskRepo.getTaskFromIDTopic(topicID);

        for(int i = 0; i< t.size(); i++){
            TaskInfo temp = new TaskInfo();
            temp.setTaskID(t.get(i).getIdTask());
            temp.setTitle(t.get(i).getTitle());
            temp.setDescription(t.get(i).getDescription());
            temp.setDeadline(t.get(i).getDeadline());
            temp.setSubmit(t.get(i).getSubmit());
            temp.setPass(t.get(i).getPass());
            temp.setStudent(getStudentDoTaskFromTaskID(t.get(i).getIdTask()));
            listTask.add(temp);
        }
        return listTask;
    }

    @Override
    public List<StudentDoTask> getAllStudentDoTaskFromTopicID(int topicID) {
        List<StudentDoTask> listStd = new ArrayList<>();
        List<StudentTopicSem> t = stdTopicSemRepo.getAllStudentByIdTopicSem(topicID);

        for (StudentTopicSem aT : t) {
            StudentDoTask temp = new StudentDoTask();
            temp.setStdName(userRepo.getUserNameFromID(aT.getIdStudent()));
            listStd.add(temp);
        }
        return listStd;
    }

    @Override
    public List<TaskInfo> getListTaskFromProf(int topicID) {
        List<TaskInfo> listTask = new ArrayList<>();
        List<Task> t = taskRepo.getTaskSubmitFromProf(topicID);

        for (Task aT : t) {
            TaskInfo temp = new TaskInfo();
            temp.setTitle(aT.getTitle());
            temp.setTaskID(aT.getIdTask());
            temp.setDescription(aT.getDescription());
            temp.setDeadline(aT.getDeadline());
            temp.setSubmit(aT.getSubmit());
            temp.setPass(aT.getPass());
            temp.setStudent(getStudentDoTaskFromTaskID(aT.getIdTask()));
            listTask.add(temp);
        }
        return listTask;
    }

    @Override
    public Task updateTaskSubmit(int taskID, int submit) {
        Task temp = taskRepo.getTaskFromTaskID(taskID);
        temp.setSubmit(submit);
        taskRepo.save(temp);
        return temp;
    }

    @Override
    public Task updateTaskPass(int taskID, int pass) {
        Task temp = taskRepo.getTaskFromTaskID(taskID);
        temp.setPass(pass);
        taskRepo.save(temp);
        return temp;
    }

    @Override
    public PageInfo getPage(int pageNumber, int topicID, Boolean isStd) {
        PageInfo page = new PageInfo();
        List<TaskInfo> listTask = new ArrayList<>();
        List<Task> t = (isStd == true) ? taskRepo.getTaskFromIDTopic(topicID) : taskRepo.getTaskSubmitFromProf(topicID);
        for (int i = 8 * pageNumber; i < min(8 * (pageNumber + 1), t.size()); i++) {

            TaskInfo temp = new TaskInfo();
            temp.setTaskID(t.get(i).getIdTask());
            temp.setTitle(t.get(i).getTitle());
            temp.setDescription(t.get(i).getDescription());
            temp.setDeadline(t.get(i).getDeadline());
            temp.setSubmit(t.get(i).getSubmit());
            temp.setPass(t.get(i).getPass());
            temp.setStudent(getStudentDoTaskFromTaskID(t.get(i).getIdTask()));
            listTask.add(temp);
        }
        int count;
        if (t.size() % 8 == 0) {
            count = t.size() / 8;
        } else {
            count = t.size() / 8 + 1;
        }
        page.setPageCount(count);
        page.setTaskList(listTask);
        return page;
    }

    @Override
    public List<TaskComment> getTaskComment(int taskID) {
        List<TaskComment> listComment = new ArrayList<>();
        List<CommentTask> t = taskCommentRepo.getCommentFromTaskID(taskID);

        for (CommentTask aT : t) {
            TaskComment temp = new TaskComment();
            User user = userRepo.getUserFromID(aT.getIdUser());
            temp.setUsername(user.getUserName());
            temp.setGender(user.getGender());
            temp.setTaskID(taskID);
            temp.setTime(aT.getTime());
            temp.setContent(aT.getContent());
            listComment.add(temp);
        }
        return listComment;
    }

    @Override
    public List<Topic> getListTopicFromStdID(int stdid) {
        List<Topic> listTopic = new ArrayList<>();
        List<StudentTopicSem> t = stdTopicSemRepo.getStudentTopicSemByIdStudent(stdid);

        for (StudentTopicSem aT : t) {
            Topic temp = topicRepo.getTopicFromTopicID(aT.getIdTopicSem());
            listTopic.add(temp);
        }
        return listTopic;
    }

    @Override
    public Topic getCurrTopicFromStdID(int stdid) {
        Integer currSem = commonService.getCurrentSem();
        if (currSem != null) {
            return topicService.getAppliedTopic(currSem, stdid);
        } else {
            return null;
        }
    }

    @Override
    public List<File> getFileNameOfFile(int taskId) {
        return fileRepo.findAllByIdTask(taskId);
    }

    @Override
    public Boolean saveFileToTask(File file) {
        Optional<Task> task = taskRepo.findById(file.getIdTask());
        if (task.isPresent()){
            Optional<File> f = fileRepo.findNameByIdTaskAndName(file.getIdTask(), file.getName(), file.getVersion());
            if (!f.isPresent()) {
                fileRepo.save(file);

            } else {
                file = f.get();
            }
            if (task.get().getReviewVersion() != null && file.getVersion() < task.get().getReviewVersion()) {
                throw new NullPointerException("Cannot Upload On Reviewed Version");
            }
            return true;
        }
        return false;
    }

    @Override
    public List<File> getFileByTaskId(Integer taskId, Integer version) {
        return fileRepo.findAllByIdTaskAndVersion(taskId, version);
    }

    @Override
    public Integer getCurrentVersionOfTaskId(Integer taskId) {
        return taskRepo.findById(taskId).map(Task::getCurrentVersion).orElse(null);
    }

    @Override
    public Integer addNewVersion(Integer taskId) {
        return taskRepo.findById(taskId).map(task -> {
            task.setCurrentVersion(task.getCurrentVersion() == null ? 0 : task.getCurrentVersion() + 1);
            taskRepo.save(task);
            return task.getCurrentVersion();
        }).orElseThrow(() -> new NullPointerException(""));
    }

    @Override
    public Integer submitVersion(Integer taskId) {
        return taskRepo.findById(taskId).map(task -> {
            task.setReviewVersion(task.getCurrentVersion());
            taskRepo.save(task);

            return  task.getReviewVersion();
        }).orElseThrow(NullPointerException::new);
    }

    @Override
    public String deleteFile(String name, Integer idTask, Integer version) {
        return fileRepo.findNameByIdTaskAndName(idTask, name,version).map(file -> {
            fileRepo.delete(file);
            return file.getName();
        }).orElseThrow(() -> new  NullPointerException("File not found"));
    }


}
