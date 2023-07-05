package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.domain.Student;
import br.com.imd.pdse.monitorando.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student save(Student student){
        return studentRepository.save(student);
    }

}
