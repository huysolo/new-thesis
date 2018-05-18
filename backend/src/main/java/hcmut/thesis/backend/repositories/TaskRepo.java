/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.repositories;
import hcmut.thesis.backend.models.Task;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 *
 * @author MinBui
 */
@Repository
public interface TaskRepo extends JpaRepository<Task, Integer>, JpaSpecificationExecutor<Task> {
    @Query("SELECT st FROM Task st WHERE st.idTopicSem = :idTopic  order by st.updateTime desc")
    List<Task> getTaskFromIDTopic(@Param("idTopic") Integer idTopic);
    
    @Query("SELECT st FROM Task st WHERE st.idTopicSem = :idTopic AND st.submit = 1  order by st.updateTime desc")
    List<Task> getTaskSubmitFromProf(@Param("idTopic") Integer idTopic);
    
    @Query("SELECT t FROM Task t WHERE t.idTask = :taskID")
    Task getTaskFromTaskID(@Param("taskID") Integer taskID);

    @Query("SELECT count (st) FROM Task st WHERE st.idTopicSem = :idTopic and st.submit = 1")
    Integer countTaskFromIDTopic(@Param("idTopic") Integer idTopic);

    @Query("SELECT st FROM Task st WHERE  st.idTopicSem = :idTopic AND  (st.pass = 1 or st.pass = 0) AND st.submit = :submit order by st.updateTime desc ")
    List<Task> findAllByIdTopicSemAndApprove(@Param("idTopic") Integer idTopic, @Param("submit") Integer submit);

}

