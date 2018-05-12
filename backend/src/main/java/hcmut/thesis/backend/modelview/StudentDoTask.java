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
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    private Integer studentId;
    private String stdName;

    public StudentDoTask(Integer studentId, String stdName) {
        this.studentId = studentId;
        this.stdName = stdName;
    }

    public String getStdName(){
       return this.stdName;
   }

   
    public void setStdName(String name){
       this.stdName = name;
   }

}
