package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.domain.Classroom;
import br.com.imd.pdse.monitorando.domain.Exercise;
import br.com.imd.pdse.monitorando.repository.ExerciseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExerciseService {

    private final ExerciseRepository repository;

    public ExerciseService(ExerciseRepository repository) {
        this.repository = repository;
    }

    public Exercise findById(UUID id) {
        if (Objects.isNull(id))
            return null;

        var classroom = repository.findById(id);

        return classroom.orElse(null);

    }

    public void softDelete(Exercise exercise){
        exercise.setActive(false);
        repository.save(exercise);
    }


    public Optional<Exercise> save(Exercise exercise){
        return Optional.of(repository.save(exercise));
    }

    public Page<Exercise> findExerciseByActive(){
        return repository.findExerciseByActive(false, Pageable.ofSize(100).withPage(0));
    }
}
