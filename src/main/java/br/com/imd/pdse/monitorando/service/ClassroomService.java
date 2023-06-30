package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.domain.Classroom;
import br.com.imd.pdse.monitorando.repository.ClassroomRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    public ClassroomService(final ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    public Optional<Classroom> findById(UUID id) throws Exception {
        if (Objects.isNull(id))
            return Optional.empty();

        return Optional.ofNullable(classroomRepository.findById(id).orElseThrow(() -> new Exception("")));
    }
}
