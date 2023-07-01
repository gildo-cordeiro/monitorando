package br.com.imd.pdse.monitorando.repository;

import br.com.imd.pdse.monitorando.domain.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubmissionRepository extends JpaRepository<Submission, UUID> {
}
