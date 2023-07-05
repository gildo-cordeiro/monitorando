package br.com.imd.pdse.monitorando.repository;

import br.com.imd.pdse.monitorando.domain.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, UUID> {

    @Query("FROM Classroom c WHERE c.monitor.uuid = :uuid OR c.teacher.uuid = :uuid")
    List<Classroom> findAllByTeacherOrMonitor(@Param("uuid") UUID uuid);

}
