package hcmut.thesis.backend.modelview;

import hcmut.thesis.backend.models.Topic;

public class TopicReview extends Topic {
    private Integer submitted;

    public TopicReview(int idTop, String title, int stNumLimit, String sumary, int idProf, Integer score, Integer semesterNo, Integer idSpecialize, Integer studentCount, Integer submitted) {
        super(idTop, title, stNumLimit, sumary, idProf, score, semesterNo, idSpecialize, studentCount);
        this.submitted = submitted;
    }

    public Integer getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Integer submitted) {
        this.submitted = submitted;
    }
}
