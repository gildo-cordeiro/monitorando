package br.com.imd.pdse.monitorando.repository;

import br.com.imd.pdse.monitorando.domain.Classroom;
import br.com.imd.pdse.monitorando.domain.Monitor;
import br.com.imd.pdse.monitorando.domain.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, UUID> {

    @Query(value = "SELECT m.classroom FROM Monitor m INNER JOIN m.user u WHERE u.userType = :userType AND m.uuid = :id")
    List<Classroom> findByIdAndUserTypeMonitor(@Param("userType") UserType userType, @Param("id") UUID id);


}
