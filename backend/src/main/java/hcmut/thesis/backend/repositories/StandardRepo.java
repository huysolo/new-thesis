package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.Standard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandardRepo extends JpaRepository<Standard, Integer> {
    @Query("SELECT st FROM Standard st WHERE st.semesterNo = :semesterNo and  st.idUser = :idUser")
    List<Standard> getAllBySemesterNoAndIAndIdUser(@Param("semesterNo") Integer semesterNo, @Param("idUser") Integer idUser);
}
