/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.modelview;

import java.sql.Timestamp;

/**
 *
 * @author MinBui
 */
public class TaskComment {
    private String username;
    private String content;
    private int taskID;
    private String gender;
    private Timestamp time;

    public String getUsername() {
        return this.username;
    }
    
     public String getContent() {
        return this.content;
    }

    public int getTaskID() {
        return this.taskID;
    }

    public String getGender() {
        return this.gender;
    }
    public Timestamp getTime(){
        return this.time;
    }
    
    public void setUsername(String name){
        this.username = name;
    }
    
     public void setContent(String content){
        this.content = content;
    }
     
    public void setTaskID(int id){
        this.taskID = id;
    }
    public void setGender (String gender){
        this.gender = gender;
    }
    public void setTime(Timestamp time){
        this.time = time;
    }
}
