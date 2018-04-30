package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.Specialize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecializeRepo extends JpaRepository<Specialize, Integer> {
    @Query("SELECT s FROM Specialize s WHERE s.idFalcuty = :idFalcuty")
    List<Specialize> findAllByIdFalcuty(@Param("idFalcuty") Integer idFalcuty);
}
