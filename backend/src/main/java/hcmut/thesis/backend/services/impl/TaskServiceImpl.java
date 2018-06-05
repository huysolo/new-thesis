/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.models.*;
import hcmut.thesis.backend.modelview.*;
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
import hcmut.thesis.backend.specifications.TaskSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
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

    @Autowired
    UserSession userSession;

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
    public PageInfo getPage(int pageNumber, int topicID, Boolean isStd, String titleFilter) {
        PageInfo page = new PageInfo();
        List<Task> listTask = new ArrayList<>();
        Specification<Task> taskSpecification = TaskSpecification.hasId(topicID).and(TaskSpecification.likeTitle(titleFilter));
        List<Task> t = (isStd) ? taskRepo.findAll(taskSpecification)
                : taskRepo.findAll(taskSpecification.and(TaskSpecification.isSubmitted()));
        for (int i = 8 * pageNumber; i < min(8 * (pageNumber + 1), t.size()); i++) {
            listTask.add(t.get(i));
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
        try {
            Integer currSem = commonService.getCurrentSem();
            return topicService.getAppliedTopic(currSem, stdid);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Task> getMyRecentTask(int topicID, int stdID) {
        List<Task> listTask = new ArrayList<>();
        List<Task> tempList = taskRepo.getTaskFromIDTopic(topicID);
        for (Task task : tempList) {
            try {
                StudentTask temp = getStudentTaskByIdTaskAndIdStudent(task.getIdTask(), stdID);
                if (temp != null) {
                    listTask.add(task);
                }
            } catch (Exception e) {

            }
        }
        return listTask;
    }

    @Override
    public Boolean saveFileToTask(File file) {
        System.out.println(file.getIdTask());
        Optional<Task> task = taskRepo.findById(file.getIdTask());

        if (task.isPresent()) {
            System.out.println(task.get().getIdTask());
            Optional<File> f = fileRepo.findNameByIdTaskAndNameAndIdUser(file.getIdTask(), file.getName(), file.getVersion(), file.getIdUser());
            if (!f.isPresent()) {
                fileRepo.save(file);

            } else {
                fileRepo.save(f.get());

            }
            return true;
        }
        return false;
    }

    @Override
    public List<File> getFileByTaskId(Integer taskId, Integer version, Integer idUser) {
        return idUser == null ? fileRepo.findAllByIdTaskAndVersionGeneral(taskId, version) : fileRepo.findAllByIdTaskAndVersion(taskId, version, idUser);
    }

    @Override
    public Integer getCurrentVersionOfTaskId(Integer taskId) {
        return getTaskByTaskId(taskId).getCurrentVersion();
    }

    @Override
    public Integer addNewVersionForStudentTask(Integer taskId, Integer idStudent) {
        StudentTask studentTask = getStudentTaskByIdTaskAndIdStudent(taskId, idStudent);
        studentTask.setCurrentVersion(studentTask.getCurrentVersion() == null ? 0 : studentTask.getCurrentVersion() + 1);
        stdTaskRepo.save(studentTask);
        return studentTask.getCurrentVersion();
    }

    @Override
    public Integer addNewVersionForGeneral(Integer taskId, Integer idStudent) {
        Task task = getTaskByTaskId(taskId);
        if (!topicService.isTeamLeader(task.getIdTopicSem(), idStudent)) {
            throw new NullPointerException("User Don't Have Permission To Add New Version");
        }
        task.setCurrentVersion(task.getCurrentVersion() == null ? 0 : task.getCurrentVersion() + 1);

        return taskRepo.save(task).getCurrentVersion();
    }

    @Override
    public String deleteFile(String name, Integer idTask, Integer version, Integer idUser) {
        Optional<File> f = idUser == null ? fileRepo.findNameByIdTaskAndNameAndIdUserGeneral(idTask, name, version)
                : fileRepo.findNameByIdTaskAndNameAndIdUser(idTask, name, version, idUser);
        return f.map(file -> {
            fileRepo.delete(file);
            return file.getName();
        }).orElseThrow(() -> new NullPointerException("File not found"));
    }

    @Override
    public StudentTask getStudentTaskByIdTaskAndIdStudent(Integer idTask, Integer idStudent) {
        return stdTaskRepo.findByIdTaskAndIdStudent(idTask, idStudent).orElseThrow(() -> new NullPointerException("Task Of Student Not Found"));
    }

    @Override
    public Task getTaskByTaskId(Integer taskId) {
        return taskRepo.findById(taskId).orElseThrow(() -> new NullPointerException("Task Not Found"));
    }

    @Override
    public List<UserUpload> getListStudentTask(Integer idTask) {
        List<UserUpload> userUploadList = new ArrayList<>();
        stdTaskRepo.getStudentDoTaskFromIDTask(idTask).forEach(studentTask -> {
            User user = userSession.getUserByIdStudent(studentTask.getIdStudent());
            userUploadList.add(new UserUpload(user.getUserName(), user.getIdUser(),
                    stdRepo.getStdIDFromUserID(user.getIdUser()), studentTask.getCurrentVersion()));
        });
        return userUploadList;
    }

    @Override
    public List<Task> findAllByIdTopicSemAndApprove(Integer idTopic, Integer approve) {
        return taskRepo.findAllByIdTopicSemAndApprove(idTopic, 1);
    }

    @Override
    public List<Task> getListTaskOfRecentTopicByApprove(Integer approve) {
        List<Task> tasks = new LinkedList<>();
        topicService.getListOpenTopic().forEach(topic -> tasks.addAll(findAllByIdTopicSemAndApprove(topic.getIdTop(), approve)));
        return tasks;
    }

    @Override
    public Integer countTaskByProf() {
        int count = 0;
        for (Topic t : topicService.getListOpenTopic()) {
            count = count + taskRepo.countSubmitTaskFromIDTopic(t.getIdTop());
        }
        return count;
    }

    @Override
    public Integer countTaskByStudent(int stdID) {
        int topicID = this.getCurrTopicFromStdID(stdID).getIdTop();
        try {
            return taskRepo.countTaskFromIDTopic(topicID);
        } catch (Exception e) {
            return 0;
        }
    }
    
    void deleteStudentTask(Integer taskID){
        List<StudentTask> list =  stdTaskRepo.getStudentDoTaskFromIDTask(taskID);
        for(StudentTask temp: list){
            stdTaskRepo.delete(temp);
        }
    }

    public String editTask(TaskInfo taskInfo) {
        try {
            Task task = taskRepo.getTaskFromTaskID(taskInfo.getTaskID());
            task.setTitle(taskInfo.getTitle());
            task.setDescription(taskInfo.getDescription());
            task.setDeadline(taskInfo.getDeadline());
            this.deleteStudentTask(taskInfo.getTaskID());
            for(Integer stdID: taskInfo.getStudentIdList()){
                StudentTask stdTask = new StudentTask(taskInfo.getTaskID(),stdID );
                stdTaskRepo.save(stdTask);
            }
            return "OK";
        } catch (Exception e) {
            return "FAIL";
        }

    }

}
