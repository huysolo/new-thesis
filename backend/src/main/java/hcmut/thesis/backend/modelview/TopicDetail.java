package hcmut.thesis.backend.modelview;

import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.models.TopicMission;
import hcmut.thesis.backend.models.TopicRequirement;

import java.util.List;

public class TopicDetail {
    private Topic topic;
    private List<TopicMission> topicMission;
    private List<TopicRequirement> topicRequirement;
    private Boolean draft;

    public Topic getTopic() {
        return topic;
    }

    public List<TopicMission> getTopicMission() {
        return topicMission;
    }

    public List<TopicRequirement> getTopicRequirement() {
        return topicRequirement;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public void setTopicMission(List<TopicMission> topicMission) {
        this.topicMission = topicMission;
    }

    public void setTopicRequirement(List<TopicRequirement> topicRequirement) {
        this.topicRequirement = topicRequirement;
    }


    public Boolean getDraft() {
        return draft;
    }

    public void setDraft(Boolean draft) {
        this.draft = draft;
    }


    public TopicDetail(Topic topic, List<TopicMission> topicMission, List<TopicRequirement> topicRequirement) {
        this.topic = topic;
        this.topicMission = topicMission;
        this.topicRequirement = topicRequirement;
        this.draft = false;
    }


}
