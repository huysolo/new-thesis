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
   private String stdName;
   private String archive;
   private Timestamp uploadDate;
   
   public String getStdName(){
       return this.stdName;
   }
   public String getArchive(){
       return this.archive;
   }
   public Timestamp getUploadDate(){
       return this.uploadDate;
   }
   
   public void setStdName(String name){
       this.stdName = name;
   }
   public void setArchive(String archive){
       this.archive = archive;
   }
   public void setUploadDate(Timestamp date){
       this.uploadDate = date;
   }
   
//   public StudentDoTask(String name, String archive, Timestamp date){
//       this.stdName = name;
//       this.archive = archive;
//       this.uploadDate = date;
//   }
}
