package hcmut.thesis.backend.services;

import hcmut.thesis.backend.models.Review;
import hcmut.thesis.backend.models.Standard;
import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.models.TopicSemStandard;
import hcmut.thesis.backend.modelview.ReviewTopic;
import hcmut.thesis.backend.modelview.TopicDetail;

import java.util.List;
public interface TopicService {
    List<Topic> getListTopicBySemester(Integer idFal, Integer semesterNo, Integer profId, Boolean available, Integer specialize);
    List<Topic> getListRecentTopicBySemester(Integer profId, Boolean available, Integer specialize);
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
    List<Standard> getGeneralStandardOfCurrentSemester();
    List<Standard> getStandardListByGeneralAndUserId(Integer userId);

}
