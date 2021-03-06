/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.services;

import hcmut.thesis.backend.models.*;
import hcmut.thesis.backend.modelview.*;

import java.util.List;

/**
 *
 * @author MinBui
 */
public interface TaskService {
    Task updateTaskSubmit(int taskID, int submit);
    Task updateTaskPass(int taskID, int pass);
    PageInfo getPage(int pageNumber, int topicID, Boolean isStd, String titleFilter);
    List<TaskComment> getTaskComment(int taskID);
    List<Topic> getListTopicFromStdID(int stdid);
    Topic getCurrTopicFromStdID(int stdid);
    List<Task> getMyRecentTask(int topicID, int stdID);
    Boolean saveFileToTask(File file);
    List<File> getFileByTaskId(Integer taskId, Integer version, Integer idUser);
    Integer getCurrentVersionOfTaskId(Integer taskId);
    Integer addNewVersionForStudentTask(Integer taskId, Integer idStudent);
    Integer addNewVersionForGeneral(Integer taskId, Integer idStudent);
    String deleteFile(String name, Integer idTask, Integer version, Integer idStudent);
    StudentTask getStudentTaskByIdTaskAndIdStudent(Integer idTask, Integer idStudent);
    Task getTaskByTaskId(Integer taskId);
    List<UserUpload> getListStudentTask(Integer idTask);
    List<Task> findAllByIdTopicSemAndApprove(Integer idTopic, Integer approve);
    List<Task> getListTaskOfRecentTopicByApprove(Integer approve);
    Integer countTaskByProf();
    Integer countTaskByStudent(int stdID);
    String editTask(TaskInfo taskInfo);
}
