package hcmut.thesis.backend.controllers;

import com.google.gson.Gson;
import hcmut.thesis.backend.models.*;
import hcmut.thesis.backend.modelview.ReviewTopic;
import hcmut.thesis.backend.modelview.StudentDoTask;
import hcmut.thesis.backend.modelview.TopicDetail;
import hcmut.thesis.backend.modelview.UserSession;
import hcmut.thesis.backend.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("topic")
public class TopicController {
    @Autowired
    TopicService topicService;

    @Autowired
    UserSession userSession;

    @RequestMapping(value = "listTopic", method = RequestMethod.GET)
    List<Topic> getListTopic(
            @RequestParam(value = "semno", required = false) Integer semno,
            @RequestParam(value = "profId", required = false) Integer profId,
            @RequestParam(value = "avail", required = false) Boolean available,
            @RequestParam(value = "spec", required = false) Integer specialize

    ){
        return topicService.getListTopicBySemester(semno, profId, available, specialize);
    }

    @RequestMapping(value = "listDraft", method = RequestMethod.GET)
    ResponseEntity<?> getListTopic(
    ){
        try {
            return ResponseEntity.ok(topicService.getDraftTopics(userSession.getProf().getIdProfessor()));
        } catch (NullPointerException e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @RequestMapping(value = "recentTopics", method = RequestMethod.GET)
    List<Topic> getListTopicRecent(
            @RequestParam(value = "profId", required = false) Integer profId,
            @RequestParam(value = "avail", required = false) Boolean available,
            @RequestParam(value = "spec", required = false) Integer specialize
    ){
        try {
            if (userSession.isUser()) {
                if (profId == null && userSession.isProf()){
                    profId = userSession.getProf().getIdProfessor();
                }
                return topicService.getListRecentTopicBySemester(profId, available, specialize);
            } else {
                return null;
            }
        } catch (NullPointerException e){
            return null;
        }
    }

    @RequestMapping(value = "topicDetail",method = RequestMethod.GET)
    ResponseEntity<?> getTopicDetail(@RequestParam(value = "topid",required = true) Integer topId){
        try {
            return ResponseEntity.ok(topicService.getTopicDetailById(topId));
        } catch (NullPointerException e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<Object> setTopicDetail(@RequestBody String topicDetail) {
        if (!userSession.isProf()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("YOU DO NOT HAVE PERMISSION TO SET TOPIC");
        }
        try {
            Gson obj = new Gson();
            TopicDetail topicDetailJS = obj.fromJson(topicDetail, TopicDetail.class);
            return ResponseEntity.ok(topicService.setTopicDetail(topicDetailJS, !topicDetailJS.getDraft()));
        } catch (EntityExistsException e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @RequestMapping(value = "publish", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<Object> setTopicDetail(@RequestBody Integer topicId) {
        try {
            return ResponseEntity.ok(topicService.publish(topicId));
        } catch (EntityExistsException e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        }catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @PostMapping(value = "apply")
    @ResponseBody
    ResponseEntity<?> applyToTopic(@RequestBody Integer topicId){
        try {
            if(!userSession.isStudent()){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("YOU DO NOT HAVE PERMISSION TO APPLY");
            }
            Topic topic =topicService.applyToTopic(topicId, userSession.getStudent().getIdStudent());
            if (topic == null){
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("INVALID REQUEST");
            }
            return ResponseEntity.ok(topic);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("INVALID REQUEST");
        }

    }

    @GetMapping(value = "appliedTopic")
    ResponseEntity<?> getAppliedTopic(@RequestParam(value = "semno", required = false) Integer semno){
        try {
            return ResponseEntity.ok(topicService.getAppliedTopic(semno,  userSession.getStudent().getIdStudent()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    
    @PostMapping(value = "reject")
    @ResponseBody
    ResponseEntity<Object> rejectToTopic(@RequestBody Integer topicId){
        if(!userSession.isStudent()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("YOU DO NOT HAVE PERMISSION TO REJECT");
        }
        Topic topic =topicService.rejectTopic(topicId, userSession.getStudent().getIdStudent());
        if (topic == null){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("INVALID REQUEST");
        }
        return ResponseEntity.ok(topic);
    }

    @GetMapping(value = "listReview")
    List<Topic> getListReviewTopic(
            @RequestParam(value = "semno", required = false) Integer semNo,
            @RequestParam(value = "submitted" , required = false) Integer isSubmitted,
            @RequestParam(value = "guide") Boolean guide
    ) {
        if (!userSession.isProf()) {
            return null;
        }
        return topicService.getListTopicReview(semNo, userSession.getProf().getIdProfessor(), isSubmitted, guide);
    }

    @DeleteMapping
    @ResponseBody
    ResponseEntity<Object> delete(@RequestParam(value = "topid") Integer topId){
        if (!userSession.isProf()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("YOU DO NOT HAVE PERMISSION TO DELETE");
        }
        topId = topicService.delete(topId);
        if (topId == null){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("INVALID REQUEST");
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("standard")
    List<Standard> gettandardByUserId(){
        if (userSession.isUser()){
            return topicService.getListStandardBySemesterAndUserId(userSession.getUserID());
        } else {
            return null;
        }
    }

    @PostMapping("standard")
    Standard addByUserId(@RequestBody Standard standard){
        return topicService.setStandard(userSession.getUserID(), standard);
    }

    @DeleteMapping("standard")
    Integer delStandard(@RequestParam(value = "id") Integer standardId) {
        if (userSession.isUser()){
            return topicService.deleteStandard(standardId, userSession.getUserID());
        } else {
            return null;
        }
    }

    @PostMapping("review")
    Review reviewTopic(@RequestBody ReviewTopic reviewTopic) {
        if (userSession.isProf()){
            return topicService.reviewTopic(reviewTopic, userSession.getProf().getIdProfessor());
        }
        return null;
    }

    @GetMapping("review")
     ResponseEntity<?> getReviewedTopicStandard(
            @RequestParam(value = "id") Integer topicId,
            @RequestParam(value = "profId", required = false) Integer profId
    ) {
        try {
            if (profId == null) {
                profId = userSession.getProf().getIdProfessor();
            }
            return ResponseEntity.ok(topicService.getListReviewedTopicStandard(topicId, profId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }

    @GetMapping("reviewCouncil")
    ResponseEntity<?> getReviewedTopicStandardForCouncil(
            @RequestParam(value = "id") Integer topicId,
            @RequestParam(value = "idCouncil") int idCouncil
    ) {
        try {
            return ResponseEntity.ok(topicService.getListReviewedTopicStandardForCouncil(topicId, idCouncil));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }

    @GetMapping("generalStandard")
    List<Standard> getGeneralStandards(@RequestParam(value = "semNo", required = false) Integer semNo) {
        return topicService.getGeneralStandardOfCurrentSemester(semNo);
    }

    @GetMapping("standardAndGeneral")
    List<Standard> standardAndGeneral() {
        if (userSession.isUser()){
            return topicService.getStandardListByGeneralAndUserId(userSession.getUserID());
        }
        return  null;
    }

    @GetMapping(value = "student")
    public List<StudentDoTask> getAllStudentDoTopic(@RequestParam(value = "id", required = false) Integer idTopic) {
        if (idTopic == null) {
            idTopic = topicService.getTopicOfCurrentSem().getIdTop();
        }
        return topicService.getAllStudentDoTaskFromTopicID(idTopic);
    }

    @GetMapping("topicCount")
    public ResponseEntity<?> getAllTopicCount() {
        try {
            return ResponseEntity.ok(topicService.countTopicByProfId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getTopicById(@RequestParam("id") Integer idTopic){
        try {
            return ResponseEntity.ok(topicService.getTopicById(idTopic));
        } catch (NullPointerException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("appliedList")
    public ResponseEntity<?> getAllTopicAppliedByStudent(){
        try {
            return ResponseEntity.ok(topicService.getAllTopicAppliedByStudent());
        } catch (NullPointerException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("listProfTopic")
    public ResponseEntity<?> getListTopicForProf(@RequestParam(value = "semNo", required = false) Integer semNo){
        try {
            return ResponseEntity.ok(topicService.getListTopicForProf(semNo));
        } catch (NullPointerException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("reviews")
    public ResponseEntity<?> getListReview(@RequestParam(value = "id") int id){
        try {
            return ResponseEntity.ok(topicService.getListReviewByIdTopic(id));
        } catch (NullPointerException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping(value = "stdgetcurrtopic")
    ResponseEntity<?> stdGetCurrentTopic(){
        Integer stdID = userSession.getStudent().getIdStudent();
        try {
            return ResponseEntity.ok(topicService.stdGetCurrentTopic(stdID));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    
    @GetMapping(value = "profgetcurrappliedtopic")
    ResponseEntity<?> profGetCurrentAppliedTopic(){
        Integer profID = userSession.getProf().getIdProfessor();
        try {
            return ResponseEntity.ok(topicService.profGetCurrAppliedListTopic(profID));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("teamlead")
    ResponseEntity<?> setTeamleader(@RequestBody int idTopic) {
        try {
            return ResponseEntity.ok(topicService.setTeamLead(idTopic));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
