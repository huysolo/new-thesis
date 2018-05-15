/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.modelview;

import hcmut.thesis.backend.models.Task;

import java.util.List;

/**
 *
 * @author MinBui
 */
public class PageInfo {
    private int pageCount;
    private List<Task> taskList;
    
    public int getPageCount(){
        return this.pageCount;
    }

    public List<Task> getTaskList(){
        return this.taskList;
    }
    
    public void setPageCount(int count){
        this.pageCount = count;
    }
    public void setTaskList(List<Task> list){
        this.taskList = list;
    }
}
