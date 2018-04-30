/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author MinBui
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.idFalcuty = :idFalcuty")
    List<User> getAllByIdFalcuty(@Param("idFalcuty") Integer idFalcuty);

    @Query("SELECT u.idFalcuty FROM  User u WHERE u.idUser = :idUser")
    Integer getIdFalcutyByIdUser(@Param("idUser") Integer idUser);

    @Query("SELECT u FROM User  u WHERE u.userName = :userName")
    User getUserByUsername(@Param("userName") String userName);

    @Query("SELECT u.userName FROM User u WHERE u.idUser = :UserID")
    String getUserNameFromID(@Param("UserID") Integer UserID);
    
    @Query("SELECT u FROM User u WHERE u.idUser = :UserID")
    User getUserFromID(@Param("UserID") Integer UserID);

}
