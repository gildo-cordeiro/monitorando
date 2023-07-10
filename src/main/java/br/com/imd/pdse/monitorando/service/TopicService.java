package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.domain.Contribution;
import br.com.imd.pdse.monitorando.domain.Submission;
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
    private final ContributionService contributionService;

    public TopicService(TopicRepository topicRepository, ContributionRepository contributionRepository, ContributionService contributionService) {
        this.topicRepository = topicRepository;
        this.contributionRepository = contributionRepository;
        this.contributionService = contributionService;
    }

    public List<Topic> getAllTopicsOrderedByLikes() {
        return topicRepository.getAllTopicsOrderedByLikes();
    }

    public Optional<Topic> save(Topic topic) {
        return Optional.of(topicRepository.save(topic));
    }

    public Optional<Contribution> save(Contribution contribution) {
        return Optional.of(contributionRepository.save(contribution));
    }

    public Topic findById(UUID id) {
        if (Objects.isNull(id))
            return null;

        var topic = topicRepository.findById(id);

        return topic.orElse(null);
    }

    public Page<Topic> findTopicsByOpen() {
        return topicRepository.findTopicsByOpen(LocalDate.now(), Pageable.ofSize(100).withPage(0));
    }

    public Page<Submission> findStudentsHelped(){
        return topicRepository.findStudentsHelped(Pageable.ofSize(100).withPage(0));
    }

    public List<Contribution> getContributionByTopic(Topic topic){
        return contributionRepository.getContributionByTopic(topic);
    }

    public int countContributionsByTopicId(UUID topicId) {
        return contributionService.countContributionsByTopicId(topicId);
    }

    public long getTotalTopics() {
        return topicRepository.count();
    }
}
