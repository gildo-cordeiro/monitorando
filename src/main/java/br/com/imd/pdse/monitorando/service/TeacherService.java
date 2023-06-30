package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.domain.Classroom;
import br.com.imd.pdse.monitorando.domain.Monitor;
import br.com.imd.pdse.monitorando.domain.Teacher;
import br.com.imd.pdse.monitorando.domain.enums.UserType;
import br.com.imd.pdse.monitorando.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Teacher save(Teacher teacher){
        return teacherRepository.save(teacher);
    }

    public Teacher findById(UUID id){
        return teacherRepository.findById(id).get();
    }

    public Teacher findTeacherByUserId(UUID id){
        return teacherRepository.findTeacherByUserId(id);
    }

    public List<Classroom> getClassroomList(UUID userId){
        return teacherRepository.findByIdAndUserTypeTeacher(UserType.TEACHER, userId);
    }
}
