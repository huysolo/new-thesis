/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.StudentTask;
import hcmut.thesis.backend.modelview.StudentDoTask;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MinBui
 */
@Repository
public interface StudentTaskRepo extends JpaRepository<StudentTask, Integer> {
    @Query("SELECT st FROM StudentTask st WHERE st.idTask = :idTask")
    List<StudentTask> getStudentDoTaskFromIDTask(@Param("idTask") Integer idTask);

    @Query("SELECT st FROM StudentTask st WHERE st.idTask = :idTask AND st.idStudent = :idStudent")
    Optional<StudentTask> findByIdTaskAndIdStudent(@Param("idTask") Integer idTask, @Param("idStudent") Integer idStudent);
}

