/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.services;

import hcmut.thesis.backend.models.File;
import hcmut.thesis.backend.models.Task;
import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.modelview.PageInfo;
import hcmut.thesis.backend.modelview.StudentDoTask;
import hcmut.thesis.backend.modelview.TaskComment;
import hcmut.thesis.backend.modelview.TaskInfo;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author MinBui
 */
public interface TaskService {
    List<StudentDoTask> getStudentDoTaskFromTaskID(int taskID);
    List<TaskInfo> getListTaskFromIDTopic(int topicID);
    List<StudentDoTask> getAllStudentDoTaskFromTopicID(int topicID);
    List<TaskInfo> getListTaskFromProf(int topicID);
    Task updateTaskSubmit(int taskID, int submit);
    Task updateTaskPass(int taskID, int pass);
    PageInfo getPage(int pageNumber, int topicID, Boolean isStd);
    List<TaskComment> getTaskComment(int taskID);
    List<Topic> getListTopicFromStdID(int stdid);
    Topic getCurrTopicFromStdID(int stdid);
    List<File> getFileNameOfFile(int taskId);
    Boolean saveFileToTask(File file);
    List<File> getFileByTaskId(Integer taskId, Integer version);
    Integer getCurrentVersionOfTaskId(Integer taskId);
    Integer addNewVersion(Integer taskId);
    String deleteFile(String name, Integer idTask, Integer version);

}
