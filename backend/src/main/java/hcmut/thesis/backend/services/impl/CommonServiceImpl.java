package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.models.Faculty;
import hcmut.thesis.backend.models.Semester;
import hcmut.thesis.backend.models.Specialize;
import hcmut.thesis.backend.models.User;
import hcmut.thesis.backend.modelview.ProfInfo;
import hcmut.thesis.backend.modelview.UserSession;
import hcmut.thesis.backend.repositories.*;
import hcmut.thesis.backend.services.CommonService;
import hcmut.thesis.backend.services.IUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private SemesterRepo semesterRepo;
    @Autowired
    private ProfessorRepo professorRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private SpecializeRepo specializeRepo;
    @Autowired
    private UserSession userSession;
    @Autowired
    private  IUserDAO userDAO;
    @Autowired
    private FacultyRepo facultyRepo;
    
    @Autowired
    private TopicRepo topicRepo;
    
    @Autowired
    private SemesterRepo semRepo;


    @Override
    public List<Semester> getListSemester() {
        return this.semesterRepo.findAll();
    }

    @Override
    public List<Integer> getAllSemester() {
        return semesterRepo.findAll().stream().map(Semester::getSemesterNo).collect(Collectors.toList());
    }

    @Override
    public List<ProfInfo> getListProfOfCurrentFaculty() {
        List<ProfInfo> result = new ArrayList<>();
        Integer idFaculty = userSession.getCurrentUserFaculty();
        professorRepo.findAll().forEach(professor -> {
            Optional<User> user = userRepo.findById(professor.getIdUser());
            if (user.isPresent()){
                if (user.get().getIdFalcuty().equals(idFaculty)){
                    ProfInfo profInfo = new ProfInfo();
                    profInfo.setUserId(user.get().getIdUser());
                    profInfo.setProfessor(userDAO.findProfByUserId(user.get().getIdUser()));
                    profInfo.setName(getFullName(user.get()));
                    result.add(profInfo);
                }
            }
        });
        return result;
    }

    @Override
    public List<ProfInfo> getListProf() {
        List<ProfInfo> result = new ArrayList<>();
        professorRepo.findAll().forEach(professor -> {
            Optional<User> user = userRepo.findById(professor.getIdUser());
            if (user.isPresent()){
                    ProfInfo profInfo = new ProfInfo();
                    profInfo.setUserId(user.get().getIdUser());
                    profInfo.setProfessor(userDAO.findProfByUserId(user.get().getIdUser()));
                    profInfo.setName(getFullName(user.get()));
                    result.add(profInfo);

            }
        });
        return result;
    }

    @Override
    public String getFullName(String fNamme, String lname) {
        return fNamme + " " + lname;
    }

    @Override
    public String getFullName(User user) {
        return getFullName(user.getFirstName(), user.getLastName());
    }

    @Override
    public Integer getCurrentApplySem() {
        List<Integer> semesters = semesterRepo.getCurrentApplySemester();
        if (semesters.size() == 0){
            throw new NullPointerException();
        }
        return semesters.get(0);
    }

    @Override
    public Integer getCurrentSem() {
        List<Integer> semesters = semesterRepo.getCurrentSemester();
        if (semesters.size() == 0){
            throw new NullPointerException();
        }
        return semesters.get(0);
    }

    @Override
    public Semester getSemOpen() {
        List<Semester> semesters = semesterRepo.getCurrentSemesterOpen();
        if (semesters.size() == 0){
            throw new NullPointerException();
        }
        return semesters.get(0);

    }

    @Override
    public List<Specialize> getAllByIdFaculty(Integer idFal) {
        return specializeRepo.findAllByIdFalcuty(idFal);
    }

    @Override
    public String getSpecByID(Integer idSpec) {
        Optional<Specialize> specialize = specializeRepo.findById(idSpec);
        return specialize.map(Specialize::getName).orElse(null);
    }

    @Override
    public String getFacultyNameById(int id) {
        return facultyRepo.findById(id).map(Faculty::getName).orElse(null);
    }
    
    @Override
    public Integer getSemFromTopicID(Integer topicID){
        try{
            return topicRepo.getTopicFromTopicID(topicID).getSemesterNo();
        } catch(Exception e){
            return null;
        }
    }
    
    @Override
    public Boolean isCurrDoTask(Integer semID){
        try {
            return true;
        } catch(Exception e){
            return true;
        }
    }
    
    @Override
    public Boolean isCurrApply(Integer semID){
        try {
            Semester check = semRepo.isCurrApplySem(semID);
            return check != null;
            
        } catch(Exception e){
            return false;
        }
    }

    @Override
    public Semester editSemester(Semester semester) {
        return semesterRepo.save(semester);
    }

    @Override
    public Integer getReviewSemester() {
        return semesterRepo.getSemesterByAtReviewTime().orElseThrow(() -> new NullPointerException("Cannot get semester review time")).getSemesterNo();
    }


}
