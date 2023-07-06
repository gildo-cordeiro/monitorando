package br.com.imd.pdse.monitorando.repository;

import br.com.imd.pdse.monitorando.domain.Comment;
import br.com.imd.pdse.monitorando.domain.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubmissionRepository extends JpaRepository<Submission, UUID> {


//    Submission findSubmissionByComment(@Param("comment") List<Comment> comment);

    @Query("SELECT s FROM Submission s WHERE s.exercise.uuid = :uuid")
    Optional<Submission> findByExerciseId(UUID uuid);
}
