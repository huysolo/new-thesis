package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.TopicSemStandard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicSemStandardRepo extends JpaRepository<TopicSemStandard, Integer> {

}
