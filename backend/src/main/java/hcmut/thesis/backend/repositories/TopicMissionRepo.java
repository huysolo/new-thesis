package hcmut.thesis.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import hcmut.thesis.backend.models.TopicMission;

import java.util.List;

@Repository
public interface TopicMissionRepo extends JpaRepository<TopicMission, Integer> {
    @Query("SELECT t FROM TopicMission t WHERE t.idTopic = :idTopic")
    List<TopicMission> findAllByTopicId(@Param("idTopic") Integer id);

}
