package hcmut.thesis.backend.controllers;

import com.google.gson.Gson;
import hcmut.thesis.backend.models.Review;
import hcmut.thesis.backend.models.Standard;
import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.models.TopicSemStandard;
import hcmut.thesis.backend.modelview.ReviewTopic;
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
        if (userSession.isUser()) {
            return topicService.getListTopicBySemester(userSession.getCurrentUserFalcuty(), semno, profId, available, specialize);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "listDraft", method = RequestMethod.GET)
    List<Topic> getListTopic(
    ){
        try {
            if (userSession.isProf()) {
                return topicService.getDraftTopics(userSession.getProf().getIdProfessor());
            } else {
                return null;
            }
        } catch (NullPointerException e){
            return null;
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
    TopicDetail getTopicDetail(@RequestParam(value = "topid",required = true) Integer topId){
        try {
            return topicService.getTopicDetailById(topId);
        } catch (NullPointerException e){
            return null;
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
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("INVALID REQUEST");
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("INVALID REQUEST");
        }
    }

    @RequestMapping(value = "publish", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<Object> setTopicDetail(@RequestBody Integer topicId) {
        if (!userSession.isProf()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("YOU DO NOT HAVE PERMISSION TO PUBLISH TOPIC");
        }
        try {
            return ResponseEntity.ok(topicService.publish(topicId));
        } catch (EntityExistsException e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("INVALID REQUEST");
        }catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("INVALID REQUEST");
        }

    }

    @PostMapping(value = "apply")
    @ResponseBody
    ResponseEntity<Object> applyToTopic(@RequestBody Integer topicId){
        try {
            if(!userSession.isStudent()){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("YOU DO NOT HAVE PERMISSION TO APPLY");
            }
            Topic topic =topicService.applyToTopic(topicId, userSession.getStudent().getIdUser());
            if (topic == null){
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("INVALID REQUEST");
            }
            return ResponseEntity.ok(topic);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("INVALID REQUEST");
        }

    }

    @GetMapping(value = "appliedTopic")
    Topic getAppliedTopic(@RequestParam(value = "semno", required = false) Integer semno){
        if(!userSession.isStudent()){
            return null;
        }
        return topicService.getAppliedTopic(semno,  userSession.getStudent().getIdUser());
    }
    @PostMapping(value = "reject")
    @ResponseBody
    ResponseEntity<Object> rejectToTopic(@RequestBody Integer topicId){
        if(!userSession.isStudent()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("YOU DO NOT HAVE PERMISSION TO REJECT");
        }
        Topic topic =topicService.rejectTopic(topicId, userSession.getStudent().getIdUser());
        if (topic == null){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("INVALID REQUEST");
        }
        return ResponseEntity.ok(topic);
    }

    @GetMapping(value = "listReview")
    List<Topic> getListReviewTopic(
            @RequestParam(value = "semno", required = false) Integer semNo,
            @RequestParam(value = "submitted" , required = false) Integer isSubmitted

    ) {
        if (!userSession.isProf()) {
            return null;
        }
        return topicService.getListTopicReview(semNo, userSession.getProf().getIdProfessor(), isSubmitted);
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
        if (userSession.isUser())
        {
            return topicService.setStandard(userSession.getUserID(), standard);
        } else {
            return null;
        }
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
    List<TopicSemStandard> getReviewedTopicStandard(@RequestParam(value = "id") Integer topicId) {
        if (userSession.isProf()) {
            return topicService.getListReviewedTopicStandard(topicId, userSession.getProf().getIdProfessor());
        }
        return null;

    }

    @GetMapping("generalStandard")
    List<Standard> getGeneralStandards() {
        return topicService.getGeneralStandardOfCurrentSemester();
    }

    @GetMapping("standardAndGeneral")
    List<Standard> standardAndGeneral() {
        if (userSession.isUser()){
            return topicService.getStandardListByGeneralAndUserId(userSession.getUserID());
        }
        return  null;
    }


}
