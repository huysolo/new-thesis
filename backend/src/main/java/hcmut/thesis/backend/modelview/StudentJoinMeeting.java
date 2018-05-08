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
public class StudentJoinMeeting {
    
    private String name;
    private int stdID;
    private int meetingID;
    
    public void setName(String name){
        this.name = name;
    }
    public void setStdID(int id){
        this.stdID = id;
    }
    public void setMeetingID(int id){
        this.meetingID = id;
    }
    
    public String getName(){
        return this.name;
    }
    public int getStdID(){
        return this.stdID;
    }
    public int getMeetingID(){
        return this.meetingID;
    }
}
