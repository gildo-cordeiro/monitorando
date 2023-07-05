package br.com.imd.pdse.monitorando.repository;

import br.com.imd.pdse.monitorando.domain.StudentTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentTeacherRepository extends JpaRepository<StudentTeacher, UUID> {
}
