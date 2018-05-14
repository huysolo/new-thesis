package hcmut.thesis.backend.repositories;

import hcmut.thesis.backend.models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepo extends JpaRepository<File, Integer> {
    @Query("SELECT f FROM File f WHERE f.idTask = :idTask")
    List<File> findAllByIdTask(@Param("idTask") Integer idTask);

    @Query("SELECT f FROM File f WHERE f.idTask = :idTask AND f.name = :name AND f.version = :version AND f.idUser = :idUser")
    Optional<File> findNameByIdTaskAndNameAndIdUser(@Param("idTask") Integer idTask, @Param("name") String name, @Param("version") Integer version, @Param("idUser") Integer idUser);

    @Query("SELECT f FROM File f WHERE f.idTask = :idTask AND f.name = :name AND f.version = :version AND f.idUser is null")
    Optional<File> findNameByIdTaskAndNameAndIdUserGeneral(@Param("idTask") Integer idTask, @Param("name") String name, @Param("version") Integer version);

    @Query("SELECT f FROM File f WHERE f.idTask = :idTask AND f.version = :version AND f.idUser = :idUser")
    List<File> findAllByIdTaskAndVersion(@Param("idTask") Integer idTask, @Param("version") Integer version, @Param("idUser") Integer idUser);

    @Query("SELECT f FROM File f WHERE f.idTask = :idTask AND f.version = :version AND f.idUser is null ")
    List<File> findAllByIdTaskAndVersionGeneral(@Param("idTask") Integer idTask, @Param("version") Integer version);
}
