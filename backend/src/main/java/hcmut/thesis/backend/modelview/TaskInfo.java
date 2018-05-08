/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.modelview;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author MinBui
 */
public class TaskInfo {
    private int taskID;
    private String title;
    private String description;
    private Timestamp deadline;
    private int submit;
    private int pass;
    private List<StudentDoTask> student;
    private Integer currentVersion;

    public Integer getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVerion(Integer currentVersion) {
        this.currentVersion = currentVersion;
    }



    public int getTaskID(){
        return this.taskID;
    }
    public String getTitle(){
        return this.title;
    }
    public String getDescription(){
        return this.description;
    }
    public List<StudentDoTask> getStudent(){
        return this.student;
    }
    public Timestamp getDeadline(){
        return this.deadline;
    }
    public int getSubmit(){
        return this.submit;
    }
    public int getPass(){
        return this.pass;
    }
    
    public void setTaskID(int id){
        this.taskID = id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setStudent(List<StudentDoTask> student){
        this.student = student;
    }
    public void setDeadline(Timestamp deadline){
        this.deadline = deadline;
    }
    public void setSubmit(int submit){
        this.submit = submit;
    }
    public void setPass(int pass){
        this.pass = pass;
    }
}
