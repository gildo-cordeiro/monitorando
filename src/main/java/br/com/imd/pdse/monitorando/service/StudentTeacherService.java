package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.domain.StudentClassroom;
import br.com.imd.pdse.monitorando.repository.StudentClassroomRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentTeacherService {

    private final StudentClassroomRepository studentClassroomRepository;

    public StudentTeacherService(StudentClassroomRepository studentClassroomRepository) {
        this.studentClassroomRepository = studentClassroomRepository;
    }

    public StudentClassroom save(StudentClassroom studentClassroom){
        return studentClassroomRepository.save(studentClassroom);
    }
}
