package hcmut.thesis.backend.services;

import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.modelview.TopicDetail;

import java.util.List;
public interface TopicService {
    List<Topic> getListTopicBySemester(Integer idFal, Integer semesterNo, Integer profId, Boolean available, Integer specialize);
    List<Topic> getListRecentTopicBySemester(Integer profId, Boolean aval, Integer specialize);
    TopicDetail getTopicDetailById(Integer topId);
    Topic setTopicDetail(TopicDetail topicDetail, Boolean publish);
    Topic applyToTopic(Integer topId, Integer studentId);
    Topic getAppliedTopic(Integer semesterNo, Integer studendId);
    Integer numberOfApply(Integer topicId);
    List<Topic> getDraftTopics(Integer profId);
    Boolean availableTopic(Topic topic);
    Topic rejectTopic(Integer topId, Integer studentId);
    Topic publish(Integer topicId);
    List<Topic> getListTopicReview(Integer semNo, Integer profId);
    Topic edit(TopicDetail topicDetail);
    Integer delete(Integer topicId);

}
