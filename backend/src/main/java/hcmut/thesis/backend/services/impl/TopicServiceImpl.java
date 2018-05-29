package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.models.*;
import hcmut.thesis.backend.modelview.*;
import hcmut.thesis.backend.repositories.*;
import hcmut.thesis.backend.services.CommonService;
import hcmut.thesis.backend.services.ITopicDAO;
import hcmut.thesis.backend.services.TaskService;
import hcmut.thesis.backend.services.TopicService;
import hcmut.thesis.backend.specifications.TopicSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    TopicRepo topicRepo;

    @Autowired
    TopicMissionRepo topicMissionRepo;

    @Autowired
    TopicReqRepo topicReqRepo;

    @Autowired
    ProfessorRepo professorRepo;

    @Autowired
    UserSession userSession;

    @Autowired
    CommonService commonService;

    @Autowired
    StudentTopicSemRepo studentTopicSemRepo;

    @Autowired
    ITopicDAO topicDAO;

    @Autowired
    StandardRepo standardRepo;

    @Autowired
    ReviewRepo reviewRepo;

    @Autowired
    TaskService taskService;

    @Autowired
    TopicSemStandardRepo  topicSemStandardRepo;

    @Override
    public List<Topic> getListTopicBySemester( Integer semesterNo, Integer profId, Boolean aval, Integer specialize) {
        aval = aval == null ? true : aval;
        return topicDAO.getFilteredTopicList(userSession.getCurrentUserFaculty(), profId, specialize, semesterNo);
    }

    @Override
    public List<Topic> getListRecentTopicBySemester(Integer profId, Boolean aval, Integer specialize) {
        Integer semNo = commonService.getCurrentApplySem();
        return getListTopicBySemester(semNo, profId, aval, specialize);

    }

    @Override
    public List<Topic> getListOpenTopic() {
        Integer semNo = commonService.getSemOpen().getSemesterNo();
        return topicRepo.findListTopicFromSemID(userSession.getProf().getIdProfessor(), semNo);
    }

    @Override
    public TopicDetail getTopicDetailById(Integer topId) {
        Topic topic = getTopicById(topId);
        List<TopicMission> topicMissionList = topicMissionRepo.findAllByTopicId(topId);
        List<TopicRequirement> topicRequirementList = topicReqRepo.findAllByTopicId(topId);
        return new TopicDetail(topic, topicMissionList, topicRequirementList);

    }

    @Override
    public Topic setTopicDetail(TopicDetail topicDetail, Boolean publish) {

        Topic topic = topicDetail.getTopic();
        topic.setIdProf(userSession.getProf().getIdProfessor());
        topic.setStudentCount(0);

        if (publish){
            topic = setPublish(topic);
        }
        if (topic == null) {
            throw new NullPointerException("Cannot set empty topic");
        }
        topicRepo.saveAndFlush(topic);
        for (TopicMission topicMis :
                topicDetail.getTopicMission()) {
            topicMis.setIdTopic(topic.getIdTop());
        }
        topicMissionRepo.saveAll(topicDetail.getTopicMission());

        for (TopicRequirement topicRequirement :
                topicDetail.getTopicRequirement()) {
            topicRequirement.setIdTopic(topic.getIdTop());
        }
        topicReqRepo.saveAll(topicDetail.getTopicRequirement());

        return topic;
    }



    @Override
    public Topic applyToTopic(Integer topId, Integer studentId) {
        Integer semester = commonService.getCurrentApplySem();
        Topic topic = getTopicById(topId);
        if (!semester.equals(topic.getSemesterNo())) {
            throw new NullPointerException("Cannot Apply To Old Topic");
        }
        List<Topic> topicList = topicRepo.findTopBySemesterNo(semester);
        for (Topic top :
                topicList) {
            if(studentTopicSemRepo.getStudentTopicSemByAll(studentId, top.getIdTop()).size() > 0){
                throw new NullPointerException("Student have already apply to this topic");
            }
            if (!availableTopic(top)){
                throw  new NullPointerException("Topic is full");
            }
        }
        topic.setStudentCount(topic.getStudentCount() + 1);
        topicRepo.save(topic);
        StudentTopicSem studentTopicSem = new StudentTopicSem();
        studentTopicSem.setIdStudent(studentId);
        studentTopicSem.setIdTopicSem(topic.getIdTop());
        studentTopicSemRepo.save(studentTopicSem);
        return topic;
    }

    @Override
    public Topic getAppliedTopic(Integer semesterNo, Integer studentId) {
        if (semesterNo == null){
            semesterNo = commonService.getCurrentApplySem();
        }
        for (Topic topic :
                topicRepo.findTopBySemesterNo(semesterNo)) {
            if (studentTopicSemRepo.getStudentTopicSemByAll(studentId, topic.getIdTop()).size() > 0){
                return topic;
            }
        }
        return null;
    }

    @Override
    public Integer numberOfApply(Integer topicId) {
        return studentTopicSemRepo.getAllStudentByIdTopicSem(topicId).size();
    }

    @Override
    public List<Topic> getDraftTopics(Integer profId) {
        return topicRepo.findAll(TopicSpecification.isDraft());
    }

    @Override
    public Boolean availableTopic(Topic topic) {
        return numberOfApply(topic.getIdTop()) < topic.getStNumLimit();
    }

    @Override
    public Topic rejectTopic(Integer topId, Integer studentId) {
        commonService.getCurrentApplySem();

        Topic topic = getTopicById(topId);

            topic.setStudentCount(topic.getStudentCount() - 1);
            topicRepo.save(topic);
            StudentTopicSem studentTopicSem = new StudentTopicSem();
            studentTopicSem.setIdTopicSem(topId);
            studentTopicSem.setIdStudent(studentId);
            studentTopicSemRepo.delete(studentTopicSem);
            return topic;


    }

    @Override
    public Topic publish(Integer topicId) {
        Integer profId = userSession.getProf().getIdProfessor();
        Topic topic = getTopicById(topicId);
        if (topic.getIdProf() != profId) {
            throw new NullPointerException("User Cannot Publish This Topic");
        }
        return topicRepo.save(setPublish(topic));

    }

    private Topic setPublish(Topic topic) {
        Integer semNo = commonService.getCurrentApplySem();
        topic.setSemesterNo(semNo);
        topic.setPublishDate(new Timestamp(System.currentTimeMillis()));
        return topic;
    }

    @Override
    public List<Topic> getListTopicReview(Integer semNo, Integer profId, Integer isSubmitted, Boolean isGuide) {
        if (semNo == null) {
            semNo = commonService.getSemOpen().getSemesterNo();
        }
        return topicDAO.getListReviewTopicByProfIdAndSemesterNo(profId, semNo, isGuide, isSubmitted);
    }

    @Override
    public Topic edit(TopicDetail topicDetail) {
        try {
            Topic topic = deleteTopicMissionAndRequirement(topicDetail.getTopic().getIdTop());
            if (topic == null) {
                return null;
            }
            setTopicDetail(topicDetail, false);
            return topic;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public Integer delete(Integer topicId) {
        Topic topic = deleteTopicMissionAndRequirement(topicId);
        topicRepo.delete(topic);
        return topicId;
    }

    @Override
    public List<Standard> getListStandardBySemesterAndUserId(Integer idUser) {
        return standardRepo.getAllBySemesterNoAndIAndIdUser(idUser);
    }

    @Override
    public Standard setStandard(Integer userId, Standard standard) {
        standard.setIdUser(userId);
        return standardRepo.saveAndFlush(standard);
    }

    @Override
    public Integer removeStandard(Integer standardId, Integer idUser) {
        Optional<Standard> standard = standardRepo.findById(standardId);
        if (standard.isPresent() && isStandardOwner(idUser, standard.get())) {
            standardRepo.delete(standard.get());
            return standardId;
        } else {
            throw new NullPointerException("Remove Condition Is Not Match");
        }
    }

    @Override
    public Review reviewTopic(ReviewTopic reviewTopic, Integer profId) {
        Optional<Review> review = reviewRepo.findReviewByIdProfAndIdTopic(profId, reviewTopic.getTopicId());
        List<TopicSemStandard> topicSemStandards = new LinkedList<>();
        double numerator = 0;
        float denominator = 0;
        if (review.isPresent() && review.get().getSubmitted() == 0){
            Topic topic = getTopicById(review.get().getIdTopic());
            if (!topic.getSemesterNo().equals(commonService.getReviewSemester())){
                throw new NullPointerException("Cannot review topic before review date");
            }
            for (StandardScore standardScore : reviewTopic.getStandardScores()) {
                Optional<Standard> standard = standardRepo.findById(standardScore.getStandardId());
                if (standard.isPresent()) {
                    TopicSemStandard topicSemStandard = new TopicSemStandard();
                    topicSemStandard.setContent(standard.get().getStName());
                    topicSemStandard.setCoefficient(standard.get().getCoefficient());
                    topicSemStandard.setScore(standardScore.getScore());
                    topicSemStandard.setIdReview(review.get().getIdReview());

                    numerator = numerator +  standard.get().getCoefficient() * standardScore.getScore();
                    denominator = denominator + standard.get().getCoefficient();
                    topicSemStandards.add(topicSemStandard);
                }


            }
            topicSemStandardRepo.saveAll(topicSemStandards);
            review.get().setSubmitted(review.get().getSubmitted() + 1);
            review.get().setScore(numerator / (denominator == 0 ? 1 : denominator));
            return reviewRepo.save(review.get());
        }
        throw new NullPointerException();
    }

    @Override
    public Standard copyStandardToCurrentSem(Standard standard) {
        return null;
    }

    @Override
    public Boolean isStandardOwner(Integer userId, Standard standard) {
        return userId.equals(standard.getIdUser());
    }

    @Override
    public Integer deleteStandard(Integer standardId, Integer idUser) {
        Optional<Standard> standard = standardRepo.findById(standardId);
        if (standard.isPresent() && standard.get().getIdUser().equals(idUser)){
            standardRepo.delete(standard.get());
            return standardId;
        }
        return null;
    }

    @Override
    public List<TopicSemStandard> getListReviewedTopicStandard(Integer topicId, Integer profId) {
        Optional<Review> review = reviewRepo.findReviewByIdProfAndIdTopic(profId, topicId);

        return review.map(review1 -> topicSemStandardRepo.findAllByIdReview(review1.getIdReview())).orElse(null);
    }

    @Override
    public List<TopicSemStandard> getListReviewedTopicStandardForCouncil(Integer topicId, Integer idCouncil) {
        Optional<Review> review = reviewRepo.findReviewByIdProfAndIdTopicForCouncil(idCouncil, topicId);

        return review.map(review1 -> topicSemStandardRepo.findAllByIdReview(review1.getIdReview())).orElse(null);
    }

    @Override
    public List<Standard> getGeneralStandardOfCurrentSemester(Integer semesterNo) {
        if (semesterNo == null) {
            semesterNo = commonService.getSemOpen().getSemesterNo();
        }
        return standardRepo.getAllBySemesterNo(semesterNo);
    }

    @Override
    public List<Standard> getStandardListByGeneralAndUserId(Integer userId) {
        Semester semester = commonService.getSemOpen();
        return standardRepo.getAllBySemesterNoAnAndIdUser(semester.getSemesterNo(), userId);
    }

    @Override
    public boolean isTeamLeader(Integer idTopic, Integer idStudent) {
        return getStudentTopicSem(idTopic, idStudent).getTeamLead() == 1;
    }

    @Override
    public StudentTopicSem getStudentTopicSem(Integer idTopic, Integer idStudent) {
        return studentTopicSemRepo.getStdTopicSemFromTopicID(idTopic, idStudent).orElseThrow(() -> new NullPointerException("Student Not Belong To Topic"));
    }

    private Topic deleteTopicMissionAndRequirement(Integer topicId) throws NullPointerException {
        TopicDetail topicDetail = getTopicDetailById(topicId);

        if (userSession.getProf().getIdProfessor() != topicDetail.getTopic().getIdProf()){
            throw new NullPointerException("User Don't Have Permission To Delete This Topic");
        }
        if (topicDetail.getTopic().getSemesterNo() != null) {
            throw new NullPointerException("Cannot Delete Published Topic");
        }
        topicDetail.getTopicMission().forEach(topicMission -> topicMissionRepo.delete(topicMission));
        topicDetail.getTopicRequirement().forEach(topicRequirement -> topicReqRepo.delete(topicRequirement));
        return topicDetail.getTopic();
    }

    @Override
    public List<StudentDoTask> getAllStudentDoTaskFromTopicID(int topicID) {
        List<StudentDoTask> listStd = new ArrayList<>();
        studentTopicSemRepo.getAllStudentByIdTopicSem(topicID).forEach(studentTopicSem -> {
            try {
                User user = userSession.getUserByIdStudent(studentTopicSem.getIdStudent());

                String fullName = commonService.getFullName(user);

                StudentDoTask studentDoTask = new StudentDoTask(studentTopicSem.getIdStudent(), fullName, user.getIdUser(), studentTopicSem.getTeamLead());
                listStd.add(studentDoTask);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        });

        return listStd;
    }

    @Override
    public Topic getTopicOfCurrentSem() {
        Integer semNo = commonService.getSemOpen().getSemesterNo();
        return getAppliedTopic(semNo, userSession.getStudent().getIdStudent());

    }

    @Override
    public Topic getTopicById(Integer idTopic) {
        return topicRepo.findById(idTopic).orElseThrow(() -> new NullPointerException("Topic Not Found"));
    }

    @Override
    public Integer countTopicByProfId() {
        return topicRepo.countTopicByIdProf(userSession.getProf().getIdProfessor());
    }

    @Override
    public List<Topic> getAllTopicAppliedByStudent() {
        List<Topic> topicList = new LinkedList<>();
        studentTopicSemRepo.getAllByIdStudent(userSession.getStudent().getIdStudent()).forEach(studentTopicSem -> {
            try {
                topicList.add(getTopicById(studentTopicSem.getIdTopicSem()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return topicList;
    }

    @Override
    public List<Topic> getListTopicForProf(Integer semesterNo) {
        Specification<Topic> topicSpecification = TopicSpecification.isProf(userSession.getProf().getIdProfessor());
        if (semesterNo != null) {
            topicSpecification = topicSpecification.and(TopicSpecification.hasSem(semesterNo));
        } else {
            topicSpecification = topicSpecification.and(TopicSpecification.isNotDraft());
        }
        return topicRepo.findAll(topicSpecification);
    }

    @Override
    public List<Review> getListReviewByIdTopic(int idTopic) {
        return reviewRepo.findReviewByIdTopic(idTopic);
    }
    
    @Override
    public Topic stdGetCurrentTopic(int stdID){
        try{
            Integer topicID = taskService.getCurrTopicFromStdID(stdID).getIdTop();
            return topicRepo.getTopicFromTopicID(topicID);
        } catch(Exception e){
            return null;
        }       
    }
    
    @Override
    public List<Topic> profGetCurrAppliedListTopic(Integer profID){
        try{
            Integer currSem = commonService.getCurrentSem();
            return topicRepo.findListAppliedTopicFromSemIDProfID(profID, currSem);
        } catch(Exception e){
            return null;
        }
    }

    @Override
    public List<StudentTopicSem> setTeamLead(int idTopic) {

        List<StudentTopicSem> studentTopicSems = getStudentTopicByIdTopic(idTopic);
        boolean isBelongToTopic = false;
        int studentId = userSession.getStudent().getIdStudent();
        for (StudentTopicSem st : studentTopicSems) {
            if (st.getIdStudent() == studentId) {
                isBelongToTopic = true;
                st.setTeamLead(1);
            } else {
                st.setTeamLead((0));
            }
        }

        if (!isBelongToTopic) {
            throw new NullPointerException("Student is Not Belong To Topic");
        }
        return studentTopicSemRepo.saveAll(studentTopicSems);
        }

    @Override
    public List<StudentTopicSem> getStudentTopicByIdTopic(int idTopic) {
        List<StudentTopicSem> studentTopicSem = studentTopicSemRepo.getAllStudentByIdTopicSem(idTopic);
        if (studentTopicSem.isEmpty()) {
            throw new NullPointerException("No Student Applies To This Topic");
        }
        return studentTopicSem;
    }

}
