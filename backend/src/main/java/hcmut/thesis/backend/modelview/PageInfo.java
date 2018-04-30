/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.modelview;

import java.util.List;

/**
 *
 * @author MinBui
 */
public class PageInfo {
    private int pageCount;
    private List<TaskInfo> taskList;
    
    public int getPageCount(){
        return this.pageCount;
    }

    public List<TaskInfo> getTaskList(){
        return this.taskList;
    }
    
    public void setPageCount(int count){
        this.pageCount = count;
    }
    public void setTaskList(List<TaskInfo> list){
        this.taskList = list;
    }
}
