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
public class StudentDoTask {
    public StudentDoTask(Integer studentId, String stdName, Integer userId, int teamlead) {
        this.studentId = studentId;
        this.stdName = stdName;
        this.userId = userId;
        this.teamlead = teamlead;
    }

    private Integer studentId;
    private String stdName;
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getTeamlead() {
        return teamlead;
    }

    public void setTeamlead(int teamlead) {
        this.teamlead = teamlead;
    }

    private int teamlead;

    public StudentDoTask(Integer studentId, String stdName, int teamlead) {
        this.studentId = studentId;
        this.stdName = stdName;
        this.teamlead = teamlead;
    }

    public StudentDoTask(Integer studentId, String stdName) {
        this.studentId = studentId;
        this.stdName = stdName;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStdName(){
       return this.stdName;
   }

   
    public void setStdName(String name){
       this.stdName = name;
   }

}
