package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.TopicRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicReqRepo extends JpaRepository<TopicRequirement, Integer> {
    @Query("SELECT t FROM TopicRequirement t WHERE t.idTopic = :idTopic")
    List<TopicRequirement> findAllByTopicId(@Param("idTopic") Integer id);
}
