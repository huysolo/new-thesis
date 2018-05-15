/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.Student;
import hcmut.thesis.backend.models.Task;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author MinBui
 */
public interface StudentRepo extends JpaRepository<Student,Integer> {
    @Query("SELECT std.idStudent FROM Student std WHERE std.idUser = :UserID")
    int getStdIDFromUserID(@Param("UserID") Integer UserID);

    @Query("SELECT std.idUser FROM Student std WHERE std.idStudent = :stdID")
    int getUserIDFromStdID(@Param("stdID") Integer stdID);

    @Query("SELECT std.idUser FROM Student std WHERE std.idStudent = :idStudent")
    Optional<Integer> findIdUserFromStudentId(@Param("idStudent") Integer idStudent);

    @Query("SELECT st FROM Student st WHERE st.idUser = :idUser")
    Optional<Student> findStudentByIdUser(@Param("idUser") Integer idUser);


}
