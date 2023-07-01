package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.domain.Classroom;
import br.com.imd.pdse.monitorando.domain.Monitor;
import br.com.imd.pdse.monitorando.repository.ClassroomRepository;
import br.com.imd.pdse.monitorando.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    private final TeacherRepository teacherRepository;

    public ClassroomService(final ClassroomRepository classroomRepository, TeacherRepository teacherRepository) {
        this.classroomRepository = classroomRepository;
        this.teacherRepository = teacherRepository;
    }

    public Classroom findById(UUID id) {
        if (Objects.isNull(id))
            return null;

        var classroom = classroomRepository.findById(id);

        return classroom.orElse(null);

    }

    public void softDelete(Classroom classroom){
        classroom.setActive(false);
        classroomRepository.save(classroom);
    }

    public Classroom save(Classroom classroom){
        return classroomRepository.save(classroom);
    }

    public List<Classroom> findAllByTeacherOrMonitor(UUID id){
        if (Objects.isNull(id))
            return Collections.emptyList();

        var classrooms = classroomRepository.findAllByTeacherOrMonitor(id);

        if (classrooms.isEmpty())
            return Collections.emptyList();

        return classroomRepository.findAllByTeacherOrMonitor(id);
    }
}
