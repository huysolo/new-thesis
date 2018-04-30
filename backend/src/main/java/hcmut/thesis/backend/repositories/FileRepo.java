package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepo extends JpaRepository<File, Number> {
    @Query("SELECT f FROM File f WHERE f.idTask = :idTask")
    List<File> findAllByIdTask(@Param("idTask") Integer idTask);
}
