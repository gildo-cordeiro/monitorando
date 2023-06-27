package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.domain.Exercise;
import br.com.imd.pdse.monitorando.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExerciseService {

    private final ExerciseRepository repository;

    public ExerciseService(ExerciseRepository repository) {
        this.repository = repository;
    }


    public Optional<Exercise> save(Exercise exercise){
        return Optional.of(repository.save(exercise));
    }
}
