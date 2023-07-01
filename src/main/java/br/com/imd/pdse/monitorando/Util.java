package br.com.imd.pdse.monitorando;

import br.com.imd.pdse.monitorando.domain.Classroom;
import br.com.imd.pdse.monitorando.domain.Monitor;
import br.com.imd.pdse.monitorando.domain.Teacher;
import br.com.imd.pdse.monitorando.domain.User;
import br.com.imd.pdse.monitorando.domain.enums.UserType;
import br.com.imd.pdse.monitorando.repository.ClassroomRepository;
import br.com.imd.pdse.monitorando.repository.MonitorRepository;
import br.com.imd.pdse.monitorando.repository.TeacherRepository;
import br.com.imd.pdse.monitorando.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Util {

    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final MonitorRepository monitorRepository;
    private final ClassroomRepository classroomRepository;
    private final PasswordEncoder passwordEncoder;

    public Util(UserRepository userRepository, TeacherRepository teacherRepository, MonitorRepository monitorRepository, ClassroomRepository classroomRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.teacherRepository = teacherRepository;
        this.monitorRepository = monitorRepository;
        this.classroomRepository = classroomRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @PostConstruct
    public void init() {
        var found = userRepository.findAll();

        if (found.isEmpty()) {
            // Teacher
            User ut1 = userRepository.save(new User("Professor Teste 1", "professor1.teste", passwordEncoder.encode("12345" ), UserType.TEACHER));

            Teacher t1 = teacherRepository.save(new Teacher(ut1));

            // Monitor
            User um1 = userRepository.save(new User("Monitor Teste 1", "monitor1.teste", passwordEncoder.encode("12345" ), UserType.MONITOR));
            User um2 = userRepository.save(new User("Monitor Teste 2", "monitor2.teste", passwordEncoder.encode("12345" ), UserType.MONITOR));
            User um3 = userRepository.save(new User("Monitor Teste 3", "monitor3.teste", passwordEncoder.encode("12345" ), UserType.MONITOR));

            Monitor m1 = monitorRepository.save(new Monitor(t1, um1));
            Monitor m2 = monitorRepository.save(new Monitor(t1, um2));
            Monitor m3 = monitorRepository.save(new Monitor(t1, um3));

            Classroom c1 = classroomRepository.save(new Classroom("PDSE - PROJETO DE DESENVOLVIMENTO DE SOTWARE EDUCACIONAL", true, m1, t1));
            Classroom c2 = classroomRepository.save(new Classroom("FMC - FUNDAMENTOS MATEMATICOS COMPUTACIONAIS", true, m2, t1));
            Classroom c3 = classroomRepository.save(new Classroom("CALCULO", true, m3, t1));
            Classroom c4 = classroomRepository.save(new Classroom("GE - GEOMETRICA EUCLIDIANA", true, m1, t1));
        }
    }
}
