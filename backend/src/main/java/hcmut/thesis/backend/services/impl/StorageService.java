package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.models.File;
import hcmut.thesis.backend.models.Student;
import hcmut.thesis.backend.models.StudentTask;
import hcmut.thesis.backend.models.User;
import hcmut.thesis.backend.modelview.UserSession;
import hcmut.thesis.backend.services.TaskService;
import hcmut.thesis.backend.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserSession userSession;
    @Autowired
    private TopicService topicService;

    public Boolean submitNewVersionAndStore(MultipartFile file, Integer taskId, boolean isGeneral) throws IOException {
        Integer idStudent = userSession.getStudent().getIdStudent();
        Integer version;
        if (isGeneral) {
            version = taskService.addNewVersionForGeneral(taskId, idStudent);
        } else {
            version = taskService.addNewVersionForStudentTask(taskId, idStudent);
        }
        return storeTask(file, taskId, version, isGeneral);
    }

    public Boolean storeTask(MultipartFile file, Integer taskId, Integer version, boolean isGeneral) throws IOException {
        Integer userId = null;
        Integer idStudent = userSession.getStudent().getIdStudent();
        if (isGeneral) {
            if (!topicService.isTeamLeader(taskService.getTaskByTaskId(taskId).getIdTopicSem(), idStudent)) {
                throw new NullPointerException("User doesn't have permission to store file on this task");
            }
        } else {
            userId = userSession.getUserID();
        }
        if (version == null) {
            if (isGeneral) {
                version = taskService.getCurrentVersionOfTaskId(taskId);
            } else {
                version = taskService.getStudentTaskByIdTaskAndIdStudent(taskId, idStudent).getCurrentVersion();
            }
        }

        File f =  new File(file.getOriginalFilename(), userId, taskId, version);
        Boolean rs = taskService.saveFileToTask(f);
        if (rs) {
            Path path;
            if (userId != null){
                path = Paths.get("upload","task", taskId.toString(), userId.toString(), version.toString());
            } else {
                path = Paths.get("upload","task", taskId.toString(), "main", version == null ? "0" : version.toString());
            }
            if (!Files.exists(path)) {

                Files.createDirectories(path);

                System.out.println("Directory created");
            } else {

                System.out.println("Directory already exists");
            }
            Files.deleteIfExists(path.resolve(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), path.resolve(file.getOriginalFilename()));
        }

        return rs;
//        try {
//
//
//            } catch (Exception e) {
//                throw new RuntimeException(e.getMessage());
//            }
    }

    public Resource loadFile(String filename, Integer taskId, Integer version, Integer idUser) {
        try {
            if (version == null) {
                version = taskService.getCurrentVersionOfTaskId(taskId);
            }
            if (version == null) {
                throw new RuntimeException("Cannot get task version");
            }

            Path file = idUser == null ?
                    Paths.get("upload","task", taskId.toString(),"main", version.toString()).resolve(filename) :
                    Paths.get("upload","task", taskId.toString(),idUser.toString(), version.toString()).resolve(filename);

            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public String deleteFile(String name, Integer idTask, Integer version, boolean isGeneral) throws IOException {
        Integer userId = null;
        Path path;
        if (isGeneral) {
            if (!topicService.isTeamLeader(taskService.getTaskByTaskId(idTask).getIdTopicSem(), userSession.getStudent().getIdStudent())) {
                throw new NullPointerException("User doesn't have permission to store file on this task");
            }
            path = Paths.get("upload","task", idTask.toString(), "main", version.toString());
        } else {
            userId = userSession.getUserID();
            path = Paths.get("upload","task", idTask.toString(), userId.toString(), version.toString());
        }
        taskService.deleteFile(name, idTask, version, userId);
        Files.deleteIfExists(path.resolve(name));
        return name;

    }
    public void init() {
//        try {
//            if (!Files.isDirectory(taskLocation)){
//                Files.createDirectories(taskLocation);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("Could not initialize storage!");
//        }
    }
}