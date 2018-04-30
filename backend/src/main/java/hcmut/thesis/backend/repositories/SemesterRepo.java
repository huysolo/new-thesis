package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemesterRepo extends JpaRepository<Semester, Integer> {
    @Query("SELECT s.semesterNo FROM Semester s WHERE s.applyOpenDate < current_timestamp AND  s.applyCloseDate > current_timestamp")
    List<Integer> getCurrentApplySemester();

    @Query("SELECT s.semesterNo FROM Semester s WHERE s.startDate < current_timestamp AND  s.endDate > current_timestamp")
    List<Integer> getCurrentSemester();

    @Query("SELECT s FROM  Semester s WHERE  s.applyCloseDate < current_timestamp")
    List<Semester> findSemesterInThePast();


}
