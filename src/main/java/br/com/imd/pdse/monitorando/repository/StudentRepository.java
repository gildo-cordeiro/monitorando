package br.com.imd.pdse.monitorando.repository;

import br.com.imd.pdse.monitorando.domain.Student;
import br.com.imd.pdse.monitorando.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    @Query("SELECT s FROM Student s WHERE s.uuid NOT IN (SELECT st.student.uuid FROM StudentClassroom st WHERE st.classroom.uuid = :id)")
    Page<Student> findStudentsThatNotInClassroom(@Param("id") UUID id, Pageable pageable);

}
