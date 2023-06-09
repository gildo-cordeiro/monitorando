package br.com.imd.pdse.monitorando.repository;

import br.com.imd.pdse.monitorando.domain.Exercise;
import br.com.imd.pdse.monitorando.domain.Submission;
import br.com.imd.pdse.monitorando.domain.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface TopicRepository extends JpaRepository<Topic, UUID> {

    @Query(value = "SELECT t FROM Topic t WHERE t.open = FALSE AND t.closedDate = :closedDate",
            countQuery = "SELECT t FROM Topic t WHERE t.open = FALSE AND t.closedDate = :closedDate")
    Page<Topic> findTopicsByOpen(@Param("closedDate") LocalDate closedDate, Pageable pageable);

    @Query(value = "SELECT s FROM Submission s INNER JOIN s.user u INNER JOIN s.exercise e INNER JOIN e.classroom.monitor m WHERE s.active = false",
            countQuery = "SELECT s FROM Submission s INNER JOIN s.user u INNER JOIN s.exercise e INNER JOIN e.classroom.monitor m WHERE s.active = false")
    Page<Submission> findStudentsHelped(Pageable pageable);

    @Query("FROM Topic t WHERE t.active = true ORDER BY t.fixed DESC, t.likes DESC")
    List<Topic> getAllTopicsOrderedByLikes();

    @Query("SELECT COUNT(c) FROM Contribution c WHERE c.topic.uuid = :topicId")
    long countContributionsByTopicId(UUID topicId);
}
