package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.repository.query.Param;

@Repository
public interface SemesterRepo extends JpaRepository<Semester, Integer> {
    @Query("SELECT s.semesterNo FROM Semester s WHERE s.applyOpenDate < current_timestamp AND  s.applyCloseDate > current_timestamp")
    List<Integer> getCurrentApplySemester();

    @Query("SELECT s.semesterNo FROM Semester s WHERE s.beginDate < current_timestamp AND  s.closeDate > current_timestamp")
    List<Integer> getCurrentSemester();

    @Query("SELECT s FROM  Semester s WHERE  s.applyCloseDate < current_timestamp")
    List<Semester> findSemesterInThePast();

    @Query("SELECT s FROM Semester s WHERE s.beginDate < current_timestamp AND  s.closeDate > current_timestamp")
    List<Semester> getCurrentSemesterOpen();
    
    @Query("SELECT s FROM Semester s WHERE s.semesterNo = :semID AND s.applyOpenDate < current_timestamp AND  s.applyCloseDate > current_timestamp")
    Semester isCurrApplySem(@Param("semID") Integer semID);
}
