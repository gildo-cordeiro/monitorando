package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.domain.Teacher;
import br.com.imd.pdse.monitorando.repository.TeacherRepository;
import org.springframework.stereotype.Service;

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

}
