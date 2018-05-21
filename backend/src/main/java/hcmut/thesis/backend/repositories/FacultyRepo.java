package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepo extends JpaRepository<Faculty, Integer> {

}
