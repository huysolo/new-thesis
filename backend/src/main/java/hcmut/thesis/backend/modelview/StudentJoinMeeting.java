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
    private Integer stdid;
    private Integer meetingid;
    
    public void setName(String name){
        this.name = name;
    }
    public void setStdID(Integer id){
        this.stdid = id;
    }
    public void setMeetingID(Integer id){
        this.meetingid = id;
    }
    
    public String getName(){
        return this.name;
    }
    public Integer getStdID(){
        return this.stdid;
    }
    public Integer getMeetingID(){
        return this.meetingid;
    }
}
