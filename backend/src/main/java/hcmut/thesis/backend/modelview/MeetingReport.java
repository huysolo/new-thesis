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
public class MeetingReport {
    private String content;
    private String plan;
    private Integer meetingID;
    
    public void setContent(String content){
        this.content = content;
    }
    public void setPlan(String plan){
        this.plan = plan;
    }
    public void setMeetingID(Integer id){
        this.meetingID = id;
    }
    
    public String getContent(){
        return this.content;
    }
    public String getPlan(){
        return this.plan;
    }
    public Integer getMeetingID(){
        return this.meetingID;
    }
}
