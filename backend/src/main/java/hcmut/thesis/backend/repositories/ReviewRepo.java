package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepo extends JpaRepository<Review, Integer> {
    @Query("SELECT r from Review r where r.idProf = :idProf and r.idTopic = :idTopic")
    Optional<Review> findReviewByIdProfAndIdTopic(@Param("idProf") Integer idProf, @Param("idTopic") Integer idTopic);

    @Query("SELECT r from Review r where r.idCouncil = :idCouncil and r.idTopic = :idTopic")
    Optional<Review> findReviewByIdProfAndIdTopicForCouncil(@Param("idCouncil") Integer idCouncil, @Param("idTopic") Integer idTopic);

    @Query("SELECT r from Review r where r.idTopic = :idTopic")
    List<Review> findReviewByIdTopic(@Param("idTopic") Integer idTopic);
}
