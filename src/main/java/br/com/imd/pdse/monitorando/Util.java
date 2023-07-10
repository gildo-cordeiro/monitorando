package br.com.imd.pdse.monitorando;

import br.com.imd.pdse.monitorando.domain.*;
import br.com.imd.pdse.monitorando.domain.enums.UserType;
import br.com.imd.pdse.monitorando.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Util {

    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final MonitorRepository monitorRepository;
    private final ClassroomRepository classroomRepository;
    private final StudentRepository studentRepository;

    private final PasswordEncoder passwordEncoder;

    public Util(UserRepository userRepository, TeacherRepository teacherRepository, MonitorRepository monitorRepository, ClassroomRepository classroomRepository, StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.teacherRepository = teacherRepository;
        this.monitorRepository = monitorRepository;
        this.classroomRepository = classroomRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        var found = userRepository.findAll();

        if (found.isEmpty()) {
            // Teacher
            User ut1 = userRepository.save(new User("Professor Ficticio 1", "professor1.ficticio", passwordEncoder.encode("12345"), UserType.TEACHER, true));
            User ut2 = userRepository.save(new User("Professor Ficticio 2", "professor2.ficticio", passwordEncoder.encode("12345"), UserType.TEACHER, true));

            Teacher t1 = teacherRepository.save(new Teacher(ut1));
            Teacher t2 = teacherRepository.save(new Teacher(ut2));


            // Monitor
            User um1 = userRepository.save(new User("Monitor Ficticio 1", "monitor1.ficticio", passwordEncoder.encode("12345"), UserType.MONITOR, true));
            User um2 = userRepository.save(new User("Monitor Ficticio 2", "monitor2.ficticio", passwordEncoder.encode("12345"), UserType.MONITOR, true));
            User um3 = userRepository.save(new User("Monitor Ficticio 3", "monitor3.ficticio", passwordEncoder.encode("12345"), UserType.MONITOR, true));

            Monitor m1 = monitorRepository.save(new Monitor(t1, um1));
            Monitor m2 = monitorRepository.save(new Monitor(t1, um2));
            Monitor m3 = monitorRepository.save(new Monitor(t2, um3));

            classroomRepository.save(new Classroom("PDSE", true, m1, t1));
            classroomRepository.save(new Classroom("FMC", true, m2, t2));
            classroomRepository.save(new Classroom("CALCULO", true, m3, t1));
            classroomRepository.save(new Classroom("GE", true, m1, t2));


            for (int i = 1; i <= 60; i++){
                Student s = new Student();
                s.setUser(new User("Aluno Ficticio " + i, "aluno"+i+".ficticio", passwordEncoder.encode("12345"), UserType.STUDENT, true));
                studentRepository.save(s);
            }
        }
    }
}
