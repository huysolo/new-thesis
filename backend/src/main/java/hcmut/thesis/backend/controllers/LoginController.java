/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.controllers;

import hcmut.thesis.backend.modelview.InfoLogin;
import hcmut.thesis.backend.services.IUserDAO;
import hcmut.thesis.backend.models.User;
import hcmut.thesis.backend.repositories.UserRepo;
import hcmut.thesis.backend.modelview.CurrUserInfo;
import hcmut.thesis.backend.modelview.UserEdit;
import hcmut.thesis.backend.modelview.UserSession;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import hcmut.thesis.backend.services.UserService;
import hcmut.thesis.backend.services.UserService;

/**
 *
 * @author MinBui
 */
@Controller
@CrossOrigin
public class LoginController {

    @Autowired
    UserService loginService;
    
     @Autowired
    UserRepo userRepo;

    @Autowired
    IUserDAO iuserDAO;
    
    @Autowired
    UserSession userSession;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CurrUserInfo checkLogin(@RequestBody InfoLogin info) {        
        CurrUserInfo currUser;
        currUser = iuserDAO.getCurrUserInfo(info.getUsername(), info.getPassword());
        return currUser;
    }
    
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    @ResponseBody
    public UserEdit editProfile(@RequestBody CurrUserInfo currUserInfo) { 
       UserEdit userEdit = iuserDAO.CheckEditUser(currUserInfo);
       if(userEdit.isEditEmail() && userEdit.isEditUsername()){
           iuserDAO.EditUser(currUserInfo);
       }
       return userEdit;
    }


}
