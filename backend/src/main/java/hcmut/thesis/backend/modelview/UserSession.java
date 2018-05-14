/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.modelview;

import hcmut.thesis.backend.models.Professor;
import hcmut.thesis.backend.models.Student;
import hcmut.thesis.backend.models.User;
import hcmut.thesis.backend.repositories.ProfessorRepo;
import hcmut.thesis.backend.repositories.StudentRepo;
import hcmut.thesis.backend.repositories.UserRepo;
import hcmut.thesis.backend.services.IUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author MinBui
 */
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class UserSession {
    @Autowired
    IUserDAO userDAO;

    @Autowired
    UserRepo userRepo;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    ProfessorRepo professorRepo;

    private int userID;
    
    public int getUserID(){
        return this.userID;
    }

    public void setUserID(int userID){
        this.userID = userID;
    }

    public Boolean isProf(){
        return professorRepo.getProfessorByIdUser(userID).isPresent();
    }

    public Boolean isStudent(){
        return studentRepo.findStudentByIdUser(userID).isPresent();
    }

    public Boolean isUser(){
        return userRepo.findById(userID).isPresent();
    }

    public Professor getProf() {return userDAO.findProfByUserId(userID);}

    public Student getStudent() {return userDAO.findStudentByUserId(userID);}

    public Integer getCurrentUserFaculty() { return userRepo.getIdFalcutyByIdUser(userID); }

    public Integer findIdUserFromStudentId(int idStudent) {
        return studentRepo.findIdUserFromStudentId(idStudent).orElseThrow(() -> new NullPointerException("User Not Found"));
    }

    public User getUserByIdStudent(Integer idStudent) {
        Integer userId = findIdUserFromStudentId(idStudent);
        return userRepo.findById(userId).orElseThrow(() -> new NullPointerException("User Not Found Of Student"));
    }
     public String getUserNameByIdStudent(Integer idStudent) {
        return getUserByIdStudent(idStudent).getUserName();
     }
}
