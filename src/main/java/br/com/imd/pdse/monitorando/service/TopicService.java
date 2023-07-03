package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.domain.Exercise;
import br.com.imd.pdse.monitorando.domain.Topic;
import br.com.imd.pdse.monitorando.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<Topic> findAll(){
        return topicRepository.findAll();
    }

    public Optional<Topic> save(Topic topic){
        return Optional.of(topicRepository.save(topic));
    }

    public Topic findById(UUID id) {
        if (Objects.isNull(id))
            return null;

        var classroom = topicRepository.findById(id);

        return classroom.orElse(null);

    }
}
