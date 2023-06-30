package br.com.imd.pdse.monitorando.repository;

import br.com.imd.pdse.monitorando.domain.Student;
import br.com.imd.pdse.monitorando.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

}
