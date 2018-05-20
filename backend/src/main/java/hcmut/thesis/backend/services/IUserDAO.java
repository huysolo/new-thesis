/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.services;

import hcmut.thesis.backend.models.Professor;
import hcmut.thesis.backend.models.Student;
import hcmut.thesis.backend.models.StudentTopicSem;
import hcmut.thesis.backend.models.User;
import hcmut.thesis.backend.modelview.CurrUserInfo;
import hcmut.thesis.backend.modelview.ManageUser;
import hcmut.thesis.backend.modelview.UserEdit;

/**
 *
 * @author MinBui
 */
public interface IUserDAO {
    User getUser(String username, String Password);
    User getUser(String username);
    Boolean checkUser(int user_id);
    CurrUserInfo getCurrUserInfo(String username, String password);
    Professor findProfByUserId(int id);
    User findUserByUserId(int id);
    Student findStudentByUserId(int id);
    UserEdit CheckEditUser(CurrUserInfo currUser);
    void EditUser(CurrUserInfo currUser);
    Boolean checkExistByStdID(int stdID);
    StudentTopicSem getStdTopicSem(int sdtid, int semid);
}