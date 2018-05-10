package hcmut.thesis.backend.services;

import hcmut.thesis.backend.models.Standard;
import hcmut.thesis.backend.models.Topic;
import hcmut.thesis.backend.models.TopicSemStandard;

import java.util.List;

public interface ITopicDAO {
    List<Topic> getFilteredTopicList(Integer idFal, Integer idProf, Integer idSpec, Integer semNo);
    List<Topic> getListReviewTopicByProfId(Integer profId, Integer submitted, Boolean isGuide);
    List<Topic> getListReviewTopicByProfId(Integer profId, Boolean isGuide);
    List<Topic> getListReviewTopicByProfIdAndSemesterNo(Integer profId, Integer semNo, Boolean isGuide, Integer submitted);
}
