package hcmut.thesis.backend.modelview;

import hcmut.thesis.backend.models.Standard;

import java.util.List;

public class ReviewTopic {
    public List<StandardScore> getStandardScores() {
        return standardScores;
    }

    public void setStandardScores(List<StandardScore> standardScores) {
        this.standardScores = standardScores;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    private List<StandardScore> standardScores;
    private Integer topicId;
}
