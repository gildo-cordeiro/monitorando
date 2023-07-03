package br.com.imd.pdse.monitorando.repository;

import br.com.imd.pdse.monitorando.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TopicRepository extends JpaRepository<Topic, UUID> {
}
