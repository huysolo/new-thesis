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
public class MeetingTimeLocation {
    String location;
    Timestamp meetingTime;
    int status;
    
    public void setLocation(String location){
        this.location = location;
    }
    public void setMeetingTime(Timestamp time){
        this.meetingTime = time;
    }
    public void setStatus(int tus){
        this.status = tus;
    }
  
    public String getLocation(){
        return this.location;
    }
    
    public Timestamp getMeetingTime(){
        return this.meetingTime;
    }
    public int getStatus(){
        return this.status;
    }
}
