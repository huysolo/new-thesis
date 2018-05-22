package hcmut.thesis.backend.services;

import hcmut.thesis.backend.models.*;
import hcmut.thesis.backend.modelview.ReviewTopic;
import hcmut.thesis.backend.modelview.StudentDoTask;
import hcmut.thesis.backend.modelview.TopicDetail;

import java.util.List;
public interface TopicService {
    List<Topic> getListTopicBySemester(Integer semesterNo, Integer profId, Boolean available, Integer specialize);
    List<Topic> getListRecentTopicBySemester(Integer profId, Boolean available, Integer specialize);
    List<Topic> getListOpenTopic();
    TopicDetail getTopicDetailById(Integer topId);
    Topic setTopicDetail(TopicDetail topicDetail, Boolean publish);
    Topic applyToTopic(Integer topId, Integer studentId);
    Topic getAppliedTopic(Integer semesterNo, Integer studentId);
    Integer numberOfApply(Integer topicId);
    List<Topic> getDraftTopics(Integer profId);
    Boolean availableTopic(Topic topic);
    Topic rejectTopic(Integer topId, Integer studentId);
    Topic publish(Integer topicId);
    List<Topic> getListTopicReview(Integer semNo, Integer profId, Integer isSubmitted,  Boolean isGuide);
    Topic edit(TopicDetail topicDetail);
    Integer delete(Integer topicId);
    List<Standard> getListStandardBySemesterAndUserId(Integer idUser);
    Standard setStandard(Integer userId, Standard standard);
    Integer removeStandard(Integer standardId, Integer idUser);
    Review reviewTopic(ReviewTopic reviewTopic, Integer profId);
    Standard copyStandardToCurrentSem(Standard standard);
    Boolean isStandardOwner(Integer userId, Standard standard);
    Integer deleteStandard(Integer standardId, Integer idUser);
    List<TopicSemStandard> getListReviewedTopicStandard(Integer topicId, Integer profId);
    List<Standard> getGeneralStandardOfCurrentSemester(Integer semesterNo);
    List<Standard> getStandardListByGeneralAndUserId(Integer userId);
    boolean isTeamLeader(Integer idTopic, Integer idStudent);
    StudentTopicSem getStudentTopicSem(Integer idTopic, Integer idStudent);
    List<StudentDoTask> getAllStudentDoTaskFromTopicID(int topicID);
    Topic getTopicOfCurrentSem();
    Topic getTopicById(Integer idTopic);
    Integer countTopicByProfId();
    List<Topic> getAllTopicAppliedByStudent();
    List<Topic> getListTopicForProf(Integer semesterNo);
    List<Review> getListReviewByIdTopic(int idTopic);
    Topic stdGetCurrentTopic(int stdID);
}
