package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.models.File;
import hcmut.thesis.backend.models.Task;
import hcmut.thesis.backend.modelview.UserSession;
import hcmut.thesis.backend.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class StorageService {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserSession userSession;


    public Boolean storeTask(MultipartFile file, Integer taskId, Integer version) {
        try {
            if (version == null) {
                version = taskService.getCurrentVersionOfTaskId(taskId);
            }
            if (version == null) {
                throw new RuntimeException("Cannot get task version");
            }

            File f = new File(file.getOriginalFilename(), userSession.getUserID(), taskId, version);
            Boolean rs = taskService.saveFileToTask(f);
            if (rs) {
                Path path = Paths.get("upload","task", taskId.toString(), version.toString());
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
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public Resource loadFile(String filename, Integer taskId, Integer version) {
        try {
            if (version == null) {
                version = taskService.getCurrentVersionOfTaskId(taskId);
            }
            if (version == null) {
                throw new RuntimeException("Cannot get task version");
            }

            Path file =  Paths.get("upload","task", taskId.toString(), version.toString()).resolve(filename);
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

    public String deleteFile(String name, Integer idTask, Integer version) throws IOException {
        taskService.deleteFile(name, idTask, version);
        Path path = Paths.get("upload","task", idTask.toString(), version.toString());
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