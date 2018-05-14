/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.services.impl;

import hcmut.thesis.backend.models.Professor;
import hcmut.thesis.backend.models.Student;
import hcmut.thesis.backend.models.StudentTopicSem;
import hcmut.thesis.backend.models.User;
import hcmut.thesis.backend.modelview.CurrUserInfo;
import hcmut.thesis.backend.modelview.UserEdit;
import hcmut.thesis.backend.repositories.ProfessorRepo;
import hcmut.thesis.backend.repositories.SemesterRepo;
import hcmut.thesis.backend.repositories.StudentRepo;
import hcmut.thesis.backend.repositories.StudentTopicSemRepo;
import hcmut.thesis.backend.repositories.TopicRepo;
import hcmut.thesis.backend.repositories.UserRepo;
import hcmut.thesis.backend.services.CommonService;
import hcmut.thesis.backend.services.IUserDAO;
import hcmut.thesis.backend.services.TopicService;
import hcmut.thesis.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author MinBui
 */
@Service
public class UserDAO implements IUserDAO {

    @Autowired
    UserRepo userRepo;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    UserService loginService;

    @Autowired
    ProfessorRepo profRepo;

    @Autowired
    StudentTopicSemRepo stdTopicSemRepo;

    @Autowired
    TopicRepo topicRepo;

    @Autowired
    SemesterRepo semRepo;

    @Autowired
    CommonService commonService;

    @Autowired
    TopicService topicService;

    @Override
    public User getUser(String username, String password) {
        List<User> listUser = userRepo.findAll();
        for (User aListUser : listUser) {
            if ((aListUser.getUserName() == null ? username == null : aListUser.getUserName().equals(username))
                    && (aListUser.getPassword() == null ? password == null : aListUser.getPassword().equals(password))) {
                return aListUser;
            }
        }
        return null;
    }

    @Override
    public Boolean checkUser(int user_id) {
        List<Student> listStudent = studentRepo.findAll();
        for (int i = 0; i < listStudent.size(); i++) {
            if (listStudent.get(i).getIdUser() == user_id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Professor findProfByUserId(int id) {
        return profRepo.getProfessorByIdUser(id).orElseThrow(() -> new NullPointerException("Professor not found"));
    }

    @Override
    public CurrUserInfo getCurrUserInfo(String username, String password) {
        CurrUserInfo currUserInfo = new CurrUserInfo();
        User user = this.getUser(username, password);
        if (user != null) {
            Boolean isStudent = this.checkUser(user.getIdUser());
            if (!isStudent) {
                Professor prof = this.findProfByUserId(user.getIdUser());
                currUserInfo.setProfID(prof.getIdProfessor());
                currUserInfo.setDegree(prof.getDegree());
                currUserInfo.setSkills(prof.getSkills());

            } else {
                
                try {
                    Integer currSemid = commonService.getCurrentSem();
                    if (this.getStdTopicSem(this.findStudentByUserId(user.getIdUser()).getIdStudent(), currSemid) != null) {
                        currUserInfo.setTeamLead(this.getStdTopicSem(this.findStudentByUserId(user.getIdUser()).getIdStudent(), currSemid).getTeamLead());
                        currUserInfo.setTopicID(this.getStdTopicSem(this.findStudentByUserId(user.getIdUser()).getIdStudent(), currSemid).getIdTopicSem());
                    }
                } catch (Exception e){
                    
                }
            }

            String isStd = (isStudent) ? "true" : "false";
            String userID = String.valueOf(user.getIdUser());

            String token = loginService.createJWT(userID);

            currUserInfo.setUsername(user.getUserName());
            currUserInfo.setPassword(user.getPassword());
            currUserInfo.setUserID(user.getIdUser());
            currUserInfo.setFirstname(user.getFirstName());
            currUserInfo.setLastname(user.getLastName());
            currUserInfo.setEmail(user.getEmail());
            currUserInfo.setGender(user.getGender());
            currUserInfo.setPhoto(user.getPhoto());

            currUserInfo.setToken(token);
            currUserInfo.setIsStudent(isStudent);
            return currUserInfo;
        }
        return null;
    }

    @Override
    public User findUserByUserId(int id) {
        List<User> listUser = userRepo.findAll();
        for (int i = 0; i < listUser.size(); i++) {
            if (listUser.get(i).getIdUser() == id) {
                return listUser.get(i);
            }
        }
        return null;
    }

    @Override
    public User getUser(String username) {
        List<User> listUser = userRepo.findAll();
        for (int i = 0; i < listUser.size(); i++) {
            if (listUser.get(i).getUserName().equals(username)) {
                return listUser.get(i);
            }
        }
        return null;
    }

    @Override
    public Student findStudentByUserId(int id) {
        return studentRepo.findStudentByIdUser(id).orElseThrow(() -> new NullPointerException("Student Not Found"));
    }

    @Override
    public UserEdit CheckEditUser(CurrUserInfo currUser) {
        UserEdit userEdit = new UserEdit();
        userEdit.setEditUsername(true);
        userEdit.setEditEmail(true);
        List<User> listUser = userRepo.findAll();
        for (int i = 0; i < listUser.size(); i++) {
            if ((listUser.get(i).getUserName().equals(currUser.getUsername())) && (listUser.get(i).getIdUser() != currUser.getUserID())) {
                userEdit.setEditUsername(false);
            }
            if ((listUser.get(i).getEmail().equals(currUser.getEmail())) && (listUser.get(i).getIdUser() != currUser.getUserID())) {
                userEdit.setEditEmail(false);
            }
        }
        return userEdit;
    }

    @Override
    public void EditUser(CurrUserInfo currUser) {
        User user = new User();
        user.setUserName(currUser.getUsername());
        user.setPassword(currUser.getPassword());
        user.setFirstName(currUser.getFistname());
        user.setLastName(currUser.getLastname());
        user.setEmail(currUser.getEmail());
        user.setIdUser(currUser.getUserID());
        user.setGender(currUser.getGender());
        if (currUser.getProfID() != 0) {
            Professor prof = new Professor();
            prof.setSkills(currUser.getSkills());
            prof.setIdProfessor(currUser.getProfID());
            prof.setDegree(currUser.getDegree());
            prof.setIdUser(currUser.getUserID());
            profRepo.save(prof);
        }
        userRepo.save(user);
    }

    @Override
    public Boolean checkExistByStdID(int stdID) {
        List<StudentTopicSem> temp = stdTopicSemRepo.findAll();
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getIdStudent() == stdID) {
                return true;
            }
        }
        return false;
    }

    @Override
    public StudentTopicSem getStdTopicSem(int stdid, int semid) {
        List<Integer> listTopicID = topicRepo.findTopIDBySemesterNo(semid);
        for (Integer aListTopicID : listTopicID) {
            Optional<StudentTopicSem> studentTopicSem = stdTopicSemRepo.getStdTopicSemFromTopicID(aListTopicID, stdid);
            if (studentTopicSem.isPresent()) {
                return studentTopicSem.get();
            }
        }
        return null;
    }

}
