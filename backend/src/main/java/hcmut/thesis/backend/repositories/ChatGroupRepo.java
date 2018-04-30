/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.ChatGroup;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author MinBui
 */
public interface ChatGroupRepo extends JpaRepository<ChatGroup, Integer> {
    @Query("SELECT cg FROM ChatGroup cg WHERE cg.idTopicSem = :topicID")
    List<ChatGroup> getAllMesageFromtopicID(@Param("topicID") Integer topicID);
}
