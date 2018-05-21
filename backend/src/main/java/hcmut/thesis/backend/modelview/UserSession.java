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

    public Integer getCurrentUserFaculty() { return userRepo.getIdFacultyByIdUser(userID).orElseThrow(() -> new NullPointerException("Faculty Not Found")); }

    public Integer findIdUserFromStudentId(int idStudent) {
        return studentRepo.findIdUserFromStudentId(idStudent).orElseThrow(() -> new NullPointerException("User Not Found"));
    }

    public User getUserByIdStudent(Integer idStudent) {
        Integer userId = findIdUserFromStudentId(idStudent);
        return userRepo.findById(userId).orElseThrow(() -> new NullPointerException("User Not Found Of Student"));
    }

    public User getUser() {
        return userRepo.findById(userID).orElseThrow(() -> new NullPointerException("User Not Found"));
    }
     public String getUserNameByIdStudent(Integer idStudent) {
        return getUserByIdStudent(idStudent).getUserName();
     }

    public ManageUser loadProfile() {
        ManageUser manageUser = new ManageUser();
        User user = getUser();
        manageUser.setUser(user);
        if (isProf()) {
            manageUser.setProfessor(getProf());
        } else if (isUser()) {
            manageUser.setStudent(getStudent());
        }
        return manageUser;
    }

    public ManageUser loadProfile(int userID) {
        ManageUser manageUser = new ManageUser();
        User user = userRepo.findById(userID).orElseThrow(() -> new NullPointerException("User Not Found`"));
        user.setPassword(null);
        manageUser.setUser(user);
        try {
            manageUser.setProfessor(userDAO.findProfByUserId(userID));
        } catch (NullPointerException e) {
            try {
                manageUser.setStudent(userDAO.findStudentByUserId(userID));
            } catch (Exception e2) {
                throw new NullPointerException(e.getMessage() + e2.getMessage());
            }
        }
        return manageUser;
    }

    public void updateProfile(ManageUser manageUser) {
        User user = manageUser.getUser();
        user.setIdUser(getUserID());
        userRepo.save(user);
        if (manageUser.getProfessor() != null) {
            Professor professor = manageUser.getProfessor();
            professor.setIdUser(getUserID());
            professor.setIdProfessor(getProf().getIdProfessor());
            professorRepo.save(professor);
        }
    }

}
