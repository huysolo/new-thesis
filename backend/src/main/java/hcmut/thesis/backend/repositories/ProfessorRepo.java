/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 *
 * @author MinBui
 */
public interface ProfessorRepo extends JpaRepository<Professor, Integer> {
    @Query("SELECT p.idProfessor FROM Professor p WHERE p.idUser = :idUser")
    Integer getProfessorIdByIdUser(@Param("idUser") Integer idUser);

    @Query("SELECT p FROM Professor p WHERE p.idUser = :idUser")
    Optional<Professor> getProfessorByIdUser(@Param("idUser") Integer idUser);
}
