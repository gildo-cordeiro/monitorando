package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.domain.Student;
import br.com.imd.pdse.monitorando.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student save(Student student){
        return studentRepository.save(student);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Page<Student> findStudentsThatNotInClassroom(UUID uuid, Pageable pageable){
        return studentRepository.findStudentsThatNotInClassroom(uuid, pageable);
    }

    public Student findById(UUID uuid){
        return studentRepository.findById(uuid).get();
    }
}
