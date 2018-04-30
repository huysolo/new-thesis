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
public class UserEdit {
    private Boolean isEditUsername;
    private Boolean isEditEmail;
    
    public Boolean isEditUsername(){
        return this.isEditUsername;
    }
     public Boolean isEditEmail(){
        return this.isEditEmail;
    }
     public void setEditUsername(Boolean isEdit){
         this.isEditUsername = isEdit;
     }
     public void setEditEmail(Boolean isEdit){
         this.isEditEmail = isEdit;
     }
     
//     public UserEdit(Boolean isUsername, Boolean isEmail){
//         this.isEditUsername = isUsername;
//         this.isEditEmail = isEmail;
//     }
}
