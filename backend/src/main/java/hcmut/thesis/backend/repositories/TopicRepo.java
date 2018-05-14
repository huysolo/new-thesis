package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TopicRepo extends JpaRepository<Topic, Integer> {

    @Query("SELECT t FROM Topic t WHERE t.semesterNo =:semesterNo")
    List<Topic> findTopBySemesterNo(@Param("semesterNo") Integer semesterNo);
    
    @Query("SELECT t.idTop FROM Topic t WHERE t.semesterNo =:semesterNo")
    List<Integer> findTopIDBySemesterNo(@Param("semesterNo") Integer semesterNo);

    @Query("SELECT t FROM Topic t WHERE t.semesterNo < :semesterNo")
    List<Topic> findAllPublish(@Param("semesterNo") Integer semesterNo);

    @Query("SELECT t FROM Topic t WHERE t.semesterNo is NULL AND t.idProf = :idProf")
    List<Topic> findAllUnPublish(@Param("idProf") Integer idProf);
    
    @Query("SELECT t FROM Topic t WHERE t.idProf = :idProf")
    List<Topic> findTopicFromProfID(@Param("idProf") Integer idProf);
    
    @Query("SELECT DISTINCT t.semesterNo FROM Topic t WHERE t.idProf = :idProf")
    List<Integer> findListSemFromProfID(@Param("idProf") Integer idProf);
    
    @Query("SELECT t FROM Topic t WHERE t.idProf = :idProf AND t.semesterNo = :semesterNo")
    List<Topic> findListTopicFromSemID(@Param("idProf") Integer idProf, @Param("semesterNo") Integer semesterNo);
    
    @Query("SELECT t FROM Topic t WHERE t.idTop = :topicid ")
    Topic getTopicFromTopicID(@Param("topicid") Integer topicid);
}

