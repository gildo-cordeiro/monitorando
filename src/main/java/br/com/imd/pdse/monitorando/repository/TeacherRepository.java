package br.com.imd.pdse.monitorando.repository;

import br.com.imd.pdse.monitorando.domain.Classroom;
import br.com.imd.pdse.monitorando.domain.Teacher;
import br.com.imd.pdse.monitorando.domain.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, UUID> {

    @Query(value = "SELECT t FROM Teacher t INNER JOIN t.user u WHERE u.uuid = :id")
    Teacher findTeacherByUserId(@Param("id") UUID id);

    @Query(value = "SELECT t.classrooms FROM Teacher t INNER JOIN t.user u WHERE u.userType = :userType AND u.uuid = :id")
    List<Classroom> findByIdAndUserTypeTeacher(@Param("userType") UserType userType, @Param("id") UUID id);

}
