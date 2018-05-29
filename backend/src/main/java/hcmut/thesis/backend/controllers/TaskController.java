/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.controllers;

import hcmut.thesis.backend.models.Semester;
import hcmut.thesis.backend.models.Task;
import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.modelview.ChatGroupInfo;
import hcmut.thesis.backend.modelview.PageInfo;
import hcmut.thesis.backend.modelview.StudentDoTask;
import hcmut.thesis.backend.modelview.TaskComment;
import hcmut.thesis.backend.modelview.TaskInfo;
import hcmut.thesis.backend.modelview.UserSession;
import hcmut.thesis.backend.repositories.ProfessorRepo;
import hcmut.thesis.backend.repositories.SemesterRepo;
import hcmut.thesis.backend.repositories.StudentRepo;
import hcmut.thesis.backend.repositories.StudentTaskRepo;
import hcmut.thesis.backend.repositories.StudentTopicSemRepo;
import hcmut.thesis.backend.repositories.TaskRepo;
import hcmut.thesis.backend.repositories.TopicRepo;
import hcmut.thesis.backend.services.ChatGroupService;
import hcmut.thesis.backend.services.CommonService;
import hcmut.thesis.backend.services.ITaskDAO;
import hcmut.thesis.backend.services.IUserDAO;
import hcmut.thesis.backend.services.TaskService;

import hcmut.thesis.backend.services.TopicService;
import org.springframework.web.bind.annotation.CrossOrigin;

import hcmut.thesis.backend.services.impl.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author MinBui
 */
@Controller
@CrossOrigin
public class TaskController {

    @Autowired
    ITaskDAO itaskDAO;

    @Autowired
    IUserDAO iuserDAO;

    @Autowired
    TaskRepo taskRepo;

    @Autowired
    TaskService taskService;

    @Autowired
    StudentTaskRepo stdTaskRepo;

    @Autowired
    StudentTopicSemRepo stdTopicSemRepo;

    @Autowired
    UserSession userSession;

    @Autowired
    StorageService storageService;

    @Autowired
    TopicRepo topicRepo;

    @Autowired
    ProfessorRepo profRepo;

    @Autowired
    StudentRepo stdRepo;

    @Autowired
    ChatGroupService chatGroupService;

    @Autowired
    SemesterRepo semRepo;

    @Autowired
    TopicService topicService;

    @Autowired
    CommonService commonService;

    @RequestMapping(value = "/crttask", method = RequestMethod.POST)
    @ResponseBody
    public Task createTask(@RequestBody TaskInfo createInfo) {
        int topicid = taskService.getCurrTopicFromStdID(userSession.getStudent().getIdStudent()).getIdTop();
        return itaskDAO.createTask(createInfo, topicid);
    }

    @RequestMapping(value = "/getlisttask", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo getListTask(@RequestParam("topicID") Integer topicID,
            @RequestParam("title") String title,
            @RequestParam("page") Integer pageNumber) {
        if (topicID == -1) {
            Integer currSem = commonService.getCurrentSem();
            if (currSem == null) {
                return null;
            }
            topicID = topicService.getAppliedTopic(currSem, userSession.getStudent().getIdStudent()).getIdTop();
        }
        
        try {
            Integer semID = commonService.getSemFromTopicID(topicID);
            if (semID != null && !commonService.isCurrApply(semID)) {
                if (userSession.isStudent()) {
                    return taskService.getPage(pageNumber, topicID, true, title);
                } else {
                    return taskService.getPage(pageNumber, topicID, false, title);
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }

    @RequestMapping(value = "/topiccount", method = RequestMethod.GET)
    @ResponseBody
    public List<Topic> getListTaskTest() {
        return topicRepo.findTopicFromProfID(profRepo.getProfessorIdByIdUser(userSession.getUserID()));
    }

    @RequestMapping(value = "/getlisttopic", method = RequestMethod.GET)
    @ResponseBody
    public List<Topic> getTopicFromSemID(@RequestParam(value = "semid") Integer semid) {
        if (semid == -1) {
            Integer currSem = commonService.getCurrentSem();
            if (currSem == null) {
                return null;
            }
            semid = currSem;
        }
        return topicRepo.findListTopicFromSemID(userSession.getProf().getIdProfessor(), semid);
    }

    @RequestMapping(value = "/stdgetlisttopic", method = RequestMethod.GET)
    @ResponseBody
    public List<Topic> getTopicFromStd() {
        return taskService.getListTopicFromStdID(userSession.getStudent().getIdStudent());
    }

    @RequestMapping(value = "/semcount", method = RequestMethod.GET)
    @ResponseBody
    public List<Integer> getListSem() {
        return topicRepo.findListSemFromProfID(userSession.getProf().getIdProfessor());
    }

    @RequestMapping(value = "/submittask")
    @ResponseBody
    public Task submitTask(@RequestParam("taskID") Integer taskID,
            @RequestParam("submit") Integer submit) {
        return taskService.updateTaskSubmit(taskID, submit);
    }

    @RequestMapping(value = "/reviewtask")
    @ResponseBody
    public Task reviewTask(@RequestParam("taskID") Integer taskID,
            @RequestParam("pass") Integer pass) {
        return taskService.updateTaskPass(taskID, pass);
    }

    @RequestMapping(value = "/getallmessage")
    @ResponseBody
    public List<ChatGroupInfo> getAllMessage() {
        int stdID = userSession.getStudent().getIdStudent();
        return chatGroupService.getchatGroupFromStdID(stdID);
    }

    @RequestMapping(value = "/gettaskcomment")
    @ResponseBody
    public List<TaskComment> getTaskComment(@RequestParam("taskid") Integer taskID) {
        return taskService.getTaskComment(taskID);
    }

    @RequestMapping(value = "/task/testsem")
    @ResponseBody
    public Semester createTasktest(@RequestParam("semID") Integer semID) {
        return semRepo.isCurrApplySem(semID);
    }

    @PostMapping("/post")
    public ResponseEntity<?> handleFileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("id") Integer taskId,
            @RequestParam(value = "ver", required = false) Integer version,
            @RequestParam("general") boolean general
    ) {
        String message;

        try {
            storageService.storeTask(file, taskId, version, general);
            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            e.printStackTrace();
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @GetMapping("getStudents")
    public ResponseEntity<?> getListStudent(@RequestParam("id") Integer idTask) {
        try {
            return ResponseEntity.ok(taskService.getListStudentTask(idTask));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        }
    }

    @PostMapping("/addversion")
    public ResponseEntity<?> addNewVersion(
            @RequestParam("general") boolean general,
            @RequestParam("id") Integer taskId,
            @RequestParam("file") MultipartFile file
    ) {

        String message;
        try {
            storageService.submitNewVersionAndStore(file, taskId, general);

            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        }
    }

    @GetMapping("/getallfiles")
    public ResponseEntity<List<String>> getListFiles(Model model,
            @RequestParam("id") Integer taskId, @RequestParam(value = "ver", required = false) Integer version,
            @RequestParam(value = "idUser", required = false) Integer idUser) {
        List<String> fileNames = taskService.getFileByTaskId(taskId, version, idUser)
                .stream().map(f -> MvcUriComponentsBuilder
                .fromMethodName(TaskController.class, "getFile", f.getName(), f.getIdTask(), f.getVersion(), f.getIdUser()).build().toString())
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(fileNames);
    }

    @GetMapping("/files/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String fileName, Integer taskId, Integer version, Integer idUser) {
        Resource file = storageService.loadFile(fileName, taskId, version, idUser);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @DeleteMapping("file")
    public ResponseEntity<String> deleteFile(
            @RequestParam("name") String name,
            @RequestParam("ver") Integer ver,
            @RequestParam("id") Integer idTask,
            @RequestParam("general") boolean general
    ) {
        try {
            return ResponseEntity.ok(storageService.deleteFile(name, idTask, ver, general));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("tasksapprove")
    public ResponseEntity<?> getAllTaskByApprove(@RequestParam("approve") Integer approve) {
        try {
            return ResponseEntity.ok(taskService.getListTaskOfRecentTopicByApprove(approve));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("taskcount")
    public ResponseEntity<?> countTaskByProf() {
        try {
            return ResponseEntity.ok(taskService.countTaskByProf());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("counttaskbystd")
    public ResponseEntity<?> countTaskByStd() {
        int stdID = userSession.getStudent().getIdStudent();
        try {
            return ResponseEntity.ok(taskService.countTaskByStudent(stdID));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("countmessagebystd")
    public ResponseEntity<?> countMessageByStd() {
        int stdID = userSession.getStudent().getIdStudent();
        try {
            return ResponseEntity.ok(chatGroupService.countMessageByStd(stdID));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("stdgettopicid")
    public ResponseEntity<?> stdGetTopicID() {
        try {
            int stdID = userSession.getStudent().getIdStudent();
            return ResponseEntity.ok(taskService.getCurrTopicFromStdID(stdID).getIdTop());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("task")
    public ResponseEntity<?> getTaskByTaskId(@RequestParam("id") Integer taskId) {
        try {
            return ResponseEntity.ok(taskService.getTaskByTaskId(taskId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/getmytasks", method = RequestMethod.GET)
    @ResponseBody
    public List<Task> getMyTasks() {
        int stdID = userSession.getStudent().getIdStudent();
        int CurrTopic = taskService.getCurrTopicFromStdID(stdID).getIdTop();
        return taskService.getMyRecentTask(CurrTopic, stdID);
    }

}
