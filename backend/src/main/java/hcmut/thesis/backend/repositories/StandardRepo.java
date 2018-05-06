package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.Standard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandardRepo extends JpaRepository<Standard, Integer> {
    @Query("SELECT st FROM Standard st WHERE st.idUser = :idUser")
    List<Standard> getAllBySemesterNoAndIAndIdUser(@Param("idUser") Integer idUser);

    @Query("SELECT st from Standard st WHERE st.semesterNo = :semesterNo")
    List<Standard> getAllBySemesterNo(@Param("semesterNo") Integer semesterNo);

    @Query("SELECT st from Standard st WHERE st.semesterNo = :semesterNo or st.idUser = :idUser")
    List<Standard> getAllBySemesterNoAnAndIdUser(@Param("semesterNo") Integer semesterNo, @Param("idUser") Integer idUser);
}
