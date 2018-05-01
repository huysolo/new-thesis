package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.TopicSemStandard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandardTopicRepo extends JpaRepository<TopicSemStandard, Integer> {
}
