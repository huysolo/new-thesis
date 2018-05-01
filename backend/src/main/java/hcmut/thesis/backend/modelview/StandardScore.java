package hcmut.thesis.backend.modelview;

import hcmut.thesis.backend.models.Standard;

public class StandardScore {
    public Integer getStandardId() {
        return standardId;
    }

    public void setStandardId(Integer standardId) {
        this.standardId = standardId;
    }

    private Integer standardId;
    private Integer score;


    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }


}
