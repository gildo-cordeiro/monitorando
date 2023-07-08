package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.repository.ContributionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContributionService {
    private final ContributionRepository contributionRepository;

    public ContributionService(ContributionRepository contributionRepository) {
        this.contributionRepository = contributionRepository;
    }

    public int countContributionsByTopicId(UUID topicId) {
        return contributionRepository.countContributionsByTopicUuid(topicId);
    }

}


