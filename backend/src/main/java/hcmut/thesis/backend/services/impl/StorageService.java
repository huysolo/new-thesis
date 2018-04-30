package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.models.File;
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

    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final Path taskLocation = Paths.get("upload", "task", "1");

    public Boolean storeTask(MultipartFile file, Integer taskId) {
        try {

            File f = new File(file.getOriginalFilename(), userSession.getUserID(), taskId);
            Boolean rs = taskService.saveFileToTask(f);
            if (rs) {
                Path path = Paths.get("upload","task", taskId.toString());
                if (!Files.exists(path)) {

                    Files.createDirectories(path);

                    System.out.println("Directory created");
                } else {

                    System.out.println("Directory already exists");
                }
                Files.copy(file.getInputStream(), path.resolve(file.getOriginalFilename()));
            }

            return rs;
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public Resource loadFile(String filename, Integer taskId) {
        try {
            Path file =  Paths.get("upload","task", taskId.toString()).resolve(filename);
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

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(taskLocation.toFile());
    }

    public void init() {
//        try {
//            if (!Files.isDirectory(taskLocation)){
//                System.out.println("Du me may");
//                Files.createDirectories(taskLocation);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("Could not initialize storage!");
//        }
    }
}