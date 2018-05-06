package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.TopicSemStandard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StandardTopicRepo extends JpaRepository<TopicSemStandard, Integer> {

}
