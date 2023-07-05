package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.domain.Classroom;
import br.com.imd.pdse.monitorando.repository.ClassroomRepository;
import br.com.imd.pdse.monitorando.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    public ClassroomService(final ClassroomRepository classroomRepository, TeacherRepository teacherRepository) {
        this.classroomRepository = classroomRepository;
    }

    public Classroom findById(UUID id) {
        if (Objects.isNull(id))
            return null;

        return classroomRepository.findById(id).get();

    }

    public void softDelete(Classroom classroom){
        classroom.setActive(false);
        classroomRepository.save(classroom);
    }

    public Classroom save(Classroom classroom){
        return classroomRepository.save(classroom);
    }

}
