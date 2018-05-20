package hcmut.thesis.backend.controllers;

import hcmut.thesis.backend.modelview.ManageUser;
import hcmut.thesis.backend.modelview.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("user")
public class UserController {
    @Autowired
    UserSession userSession;

    @GetMapping("profile")
    ResponseEntity<?> loadProfile() {
        try {
            return ResponseEntity.ok(userSession.loadProfile());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("profile")
    ResponseEntity<?> updateProfile(@RequestBody ManageUser manageUser) {
        try {
            userSession.updapteProfile(manageUser);
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
