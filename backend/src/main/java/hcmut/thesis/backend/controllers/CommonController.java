package hcmut.thesis.backend.controllers;

import hcmut.thesis.backend.models.Semester;
import hcmut.thesis.backend.models.Specialize;
import hcmut.thesis.backend.modelview.ProfInfo;
import hcmut.thesis.backend.modelview.UserSession;
import hcmut.thesis.backend.repositories.StudentTopicSemRepo;
import hcmut.thesis.backend.services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CommonController {
    @Autowired
    CommonService commonService;
    @Autowired
    UserSession userSession;
    
     @Autowired
    StudentTopicSemRepo stdTopicSemRepo;
    StudentTopicSemRepo studentTopicSemRepo;

    @RequestMapping(value = "listSemester", method = RequestMethod.GET)
    List<Semester> getListSemster(){
        return commonService.getListSemester();
    }
    @GetMapping("allSemNo")
    List<Integer> getListSemNo(){
        return commonService.getAllSemester();
    }

    @RequestMapping(value = "listProf", method = RequestMethod.GET)
    List<ProfInfo> getListProf(){
        return commonService.getListProf();

    }

    @RequestMapping(value = "currentSem", method = RequestMethod.GET)
    Integer getCurrentTopicSemester(){
        return commonService.getCurrentApplySem();
    }

    @RequestMapping(value = "listSpec", method = RequestMethod.GET)
    List<Specialize> getListSpec(){
        if (!userSession.isUser()){
            return null;
        }
        return commonService.getAllByIdFaculty(userSession.getCurrentUserFaculty());
    }

    @RequestMapping(value = "spec", method = RequestMethod.GET)
    String getSpecNameById(@RequestParam("specId") Integer specId){
        return commonService.getSpecByID(specId);
    }


}
