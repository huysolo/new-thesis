/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.modelview;

import hcmut.thesis.backend.models.MeetingSchelule;
import java.util.List;

/**
 *
 * @author MinBui
 */
public class MeetingInfo {

    private String title;
    private Integer meetingid;
    private String content;
    private String reason;
    private Integer topicID;
    private Integer status;
    private String reportContent;
    private String reportPlan;
    private List<StudentJoinMeeting> student;
    private List<MeetingTimeLocation> timeLocation;
    
    public void setTitle(String title){
        this.title = title;
    }
    public void setMeetingID(Integer id){
        this.meetingid = id;
    }
    public void setContent(String cont){
        this.content = cont;
    }
    public void setReason(String reason){
        this.reason = reason;
    }
    public void setReportContent(String content){
        this.reportContent = content;
    }
    public void setReportPlan(String plan){
        this.reportPlan = plan;
    }
    public void setTopicID(Integer id){
        this.topicID = id;
    }
    public void setStatus(Integer tus){
        this.status = tus;
    }
    public void setStudent(List<StudentJoinMeeting> std){
        this.student = std;
    }
    public void setTimeLocation(List<MeetingTimeLocation> timelocation){
        this.timeLocation = timelocation;
    }
    
    public String getTitle(){
        return this.title;
    }
    public Integer getMeetingID(){
        return this.meetingid;
    }
    public String getContent(){
        return this.content;
    }
    public String getReason(){
        return this.reason;
    }
    public String getReportContent(){
        return this.reportContent;
    }
    public String getReportPlan(){
        return this.reportPlan;
    }
    public Integer getTopicID(){
        return this.topicID;
    }
    public Integer getStatus(){
        return this.status;
    }
    public List<StudentJoinMeeting> getStudent(){
        return this.student;
    }
    public List<MeetingTimeLocation> getTimeLocation(){
        return this.timeLocation;
    }
    

}
