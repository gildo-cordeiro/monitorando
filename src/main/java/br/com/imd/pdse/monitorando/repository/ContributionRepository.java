package br.com.imd.pdse.monitorando.repository;

import br.com.imd.pdse.monitorando.domain.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, UUID> {
    int countContributionsByTopicUuid(UUID topicUuid);
}

