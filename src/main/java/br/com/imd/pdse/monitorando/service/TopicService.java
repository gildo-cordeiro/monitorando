package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.domain.Contribution;
import br.com.imd.pdse.monitorando.domain.Topic;
import br.com.imd.pdse.monitorando.repository.ContributionRepository;
import br.com.imd.pdse.monitorando.repository.TopicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    private final ContributionRepository contributionRepository;

    public TopicService(TopicRepository topicRepository, ContributionRepository contributionRepository) {
        this.topicRepository = topicRepository;
        this.contributionRepository = contributionRepository;
    }

    public List<Topic> findAll(){
        return topicRepository.findAll();
    }

    public Optional<Topic> save(Topic topic){
        return Optional.of(topicRepository.save(topic));
    }
    public Optional<Contribution> save(Contribution contribution){
        return Optional.of(contributionRepository.save(contribution));
    }


    public Topic findById(UUID id) {
        if (Objects.isNull(id))
            return null;

        var classroom = topicRepository.findById(id);

        return classroom.orElse(null);

    }

    public Page<Topic> findTopicsByOpen(){
        return topicRepository.findTopicsByOpen(LocalDate.now(), Pageable.ofSize(100).withPage(0));
    }

    public List<Contribution> findAllContributions(){
        return contributionRepository.findAll();
    }
}
