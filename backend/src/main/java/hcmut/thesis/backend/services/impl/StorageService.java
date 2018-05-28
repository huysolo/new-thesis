package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.models.File;

import hcmut.thesis.backend.modelview.UserSession;
import hcmut.thesis.backend.services.TaskService;
import hcmut.thesis.backend.services.TopicService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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

    public Boolean storeAvatar(MultipartFile file) throws IOException {
        Path path = Paths.get("upload","avatar", Integer.toString(userSession.getUserID()));
        if (!Files.exists(path)) {

            Files.createDirectories(path);
        } else {
            FileUtils.cleanDirectory(path.toFile());
        }

        Files.copy(file.getInputStream(), path.resolve(file.getOriginalFilename()));
        return true;
    }

    public String getAvatarName(int userId) throws IOException {
        Path file = Paths.get("upload","avatar", Integer.toString(userId));

        List<Path> paths = new LinkedList<>();
        Files.list(file).forEach(((LinkedList<Path>) paths)::push);
        return ((LinkedList<Path>) paths).getFirst().getName(3).toString();
    }

    public Resource loadAvatar(String userId) throws IOException {
        Path file = Paths.get("upload","avatar", userId);

        List<Path> paths = new LinkedList<>();
        Files.list(file).forEach(((LinkedList<Path>) paths)::push);

            try {
                Resource resource = new UrlResource(((LinkedList<Path>) paths).getFirst().toUri());
                if (resource.exists() || resource.isReadable()) {
                    return resource;
                } else {
                    throw new RuntimeException("FAIL!");
                }


        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
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

    }
}