/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.StudentTopicSem;
import hcmut.thesis.backend.models.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MinBui
 */
@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {
    @Query("SELECT st FROM Task st WHERE st.idTopicSem = :idTopic")
    List<Task> getTaskFromIDTopic(@Param("idTopic") Integer idTopic);
    
    @Query("SELECT st FROM Task st WHERE st.idTopicSem = :idTopic AND st.submit = 1")
    List<Task> getTaskSubmitFromProf(@Param("idTopic") Integer idTopic);
    
    @Query("SELECT t FROM Task t WHERE t.idTask = :taskID")
    Task getTaskFromTaskID(@Param("taskID") Integer taskID);

    @Query("SELECT st FROM Task st WHERE  st.idTopicSem = :idTopic AND  st.pass = :pass  AND st.submit = :submit order by st.updateTime")
    List<Task> findAllByIdTopicSemAndApprove(@Param("idTopic") Integer idTopic, @Param("pass") Integer pass, @Param("submit") Integer submit);

}

