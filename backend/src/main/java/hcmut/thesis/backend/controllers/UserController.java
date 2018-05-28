package hcmut.thesis.backend.controllers;

import hcmut.thesis.backend.modelview.ManageUser;
import hcmut.thesis.backend.modelview.UserSession;
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

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
@RequestMapping("user")
public class UserController {
    @Autowired
    UserSession userSession;
    @Autowired
    StorageService storageService;

    @GetMapping("profile")
    ResponseEntity<?> loadProfile(@RequestParam(value = "id", required = false) Integer id) {
        try {
            if (id != null) {
                return ResponseEntity.ok(userSession.loadProfile(id));
            }
            return ResponseEntity.ok(userSession.loadProfile());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("profile")
    ResponseEntity<?> updateProfile(@RequestBody ManageUser manageUser) {
        try {
            userSession.updateProfile(manageUser);
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/avatar")
    public ResponseEntity<List<String>> getListFiles(Model model, @RequestParam("id") int userId)   {
        List<String> names = new ArrayList<>();
        names.add(Integer.toString(userId));
        List<String> avatars = names
                .stream().map(name -> MvcUriComponentsBuilder
                        .fromMethodName(UserController.class, "getListAllAvatar", name).build().toString())
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(avatars);
    }

    @GetMapping("/avatars/{id:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getListAllAvatar(@PathVariable String id) throws IOException {
        try {
            Resource resource = storageService.loadAvatar(id);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (NoSuchFileException e) {
            return ResponseEntity.noContent().build();
        }

    }

    @PostMapping("/avatar")
    public ResponseEntity<?> addAvatar( @RequestParam("file") MultipartFile file
    ){

        String message;
        try {
            storageService.storeAvatar(file);

            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        }
    }

}
