/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.modelview;

/**
 *
 * @author MinBui
 */
public class InfoLogin {
    String username;
    String password;
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setUsername(String username){
        this.username = username;
    }
}
