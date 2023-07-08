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
    private final StudentClassroomRepository studentClassroomRepository;

    private final PasswordEncoder passwordEncoder;

    public Util(UserRepository userRepository, TeacherRepository teacherRepository, MonitorRepository monitorRepository, ClassroomRepository classroomRepository, StudentRepository studentRepository, StudentClassroomRepository studentClassroomRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.teacherRepository = teacherRepository;
        this.monitorRepository = monitorRepository;
        this.classroomRepository = classroomRepository;
        this.studentRepository = studentRepository;
        this.studentClassroomRepository = studentClassroomRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        var found = userRepository.findAll();

        if (found.isEmpty()) {
            // Teacher
            User ut1 = userRepository.save(new User("Professor Teste 1", "professor1.teste", passwordEncoder.encode("12345"), UserType.TEACHER, true));
            User ut2 = userRepository.save(new User("Professor Teste 2", "professor2.teste", passwordEncoder.encode("12345"), UserType.TEACHER, true));

            Teacher t1 = teacherRepository.save(new Teacher(ut1));
            Teacher t2 = teacherRepository.save(new Teacher(ut2));


            // Monitor
            User um1 = userRepository.save(new User("Monitor Teste 1", "monitor1.teste", passwordEncoder.encode("12345"), UserType.MONITOR, true));
            User um2 = userRepository.save(new User("Monitor Teste 2", "monitor2.teste", passwordEncoder.encode("12345"), UserType.MONITOR, true));
            User um3 = userRepository.save(new User("Monitor Teste 3", "monitor3.teste", passwordEncoder.encode("12345"), UserType.MONITOR, true));

            Monitor m1 = monitorRepository.save(new Monitor(t1, um1));
            Monitor m2 = monitorRepository.save(new Monitor(t1, um2));
            Monitor m3 = monitorRepository.save(new Monitor(t2, um3));

            Classroom c1 = classroomRepository.save(new Classroom("PDSE - PROJETO DE DESENVOLVIMENTO DE SOTWARE EDUCACIONAL", true, m1, t1));
            Classroom c2 = classroomRepository.save(new Classroom("FMC - FUNDAMENTOS MATEMATICOS COMPUTACIONAIS", true, m2, t2));
            Classroom c3 = classroomRepository.save(new Classroom("CALCULO", true, m3, t1));
            Classroom c4 = classroomRepository.save(new Classroom("GE - GEOMETRICA EUCLIDIANA", true, m1, t2));


            for (int i = 1; i <= 10; i++){
                Student s = new Student();
                s.setUser(new User("Student Teste " + i, "student"+i+".teste", passwordEncoder.encode("12345"), UserType.STUDENT, true));
                studentRepository.save(s);
            }
//            /*--------------------------------------------------------------------------------*/
//
//            Student s1 = new Student();
//            s1.setUser(new User("Student Teste 1", "student1.teste", passwordEncoder.encode("12345"), UserType.STUDENT));
//            s1.setClassrooms(List.of(c1, c3, c4));
//            var saved1 = studentRepository.save(s1);
//
//            Student s2 = new Student();
//            s2.setUser(new User("Student Teste 2", "student2.teste", passwordEncoder.encode("12345"), UserType.STUDENT));
//            s2.setClassrooms(List.of(c1, c2, c4));
//            var saved2 = studentRepository.save(s2);
//
//            Student s3 = new Student();
//            s3.setUser(new User("Student Teste 3", "student3.teste", passwordEncoder.encode("12345"), UserType.STUDENT));
//            s3.setClassrooms(List.of(c1, c3, c2));
//            var saved3 = studentRepository.save(s3);
//
//            /*--------------------------------------------------------------------------------*/
//
//            StudentClassroom st1 = new StudentClassroom();
//            st1.setStudent(saved1);
//            st1.setTeacher(t1);
//            var studentTeacher1 = studentTeacherRepository.save(st1);
//
//            StudentClassroom st2 = new StudentClassroom();
//            st2.setStudent(saved2);
//            st2.setTeacher(t2);
//            var studentTeacher2 = studentTeacherRepository.save(st2);
//
//            StudentClassroom st3 = new StudentClassroom();
//            st3.setStudent(saved1);
//            st3.setTeacher(t2);
//            var studentTeacher3 = studentTeacherRepository.save(st3);
//
//            StudentClassroom st4 = new StudentClassroom();
//            st4.setStudent(saved3);
//            st4.setTeacher(t1);
//            var studentTeacher4 = studentTeacherRepository.save(st4);
//
//            /*--------------------------------------------------------------------------------*/
//
//            var teacher1 = teacherRepository.findById(t1.getUuid()).get();
//            teacher1.setTeachers(Set.of(studentTeacher1, studentTeacher3, studentTeacher4));
//            teacherRepository.save(teacher1);
//
//            var teacher2 = teacherRepository.findById(t2.getUuid()).get();
//            teacher1.setTeachers(Set.of(studentTeacher3, studentTeacher2));
//            teacherRepository.save(teacher2);
//
//            /*--------------------------------------------------------------------------------*/
//
//            var student1 = studentRepository.findById(saved1.getUuid()).get();
//            student1.setStudents(Set.of(studentTeacher1, studentTeacher3));
//            studentRepository.save(student1);
//
//            var student2 = studentRepository.findById(saved2.getUuid()).get();
//            student1.setStudents(Set.of(studentTeacher2));
//            studentRepository.save(student2);
//
//            var student3 = studentRepository.findById(saved3.getUuid()).get();
//            student1.setStudents(Set.of(studentTeacher3));
//            studentRepository.save(student3);
//
//            /*--------------------------------------------------------------------------------*/
//
//
//            var classroom1 = classroomRepository.findById(c1.getUuid()).get();
//            classroom1.setStudents(List.of(saved1, saved2, saved3));
//            classroomRepository.save(c1);
//
//
//            var classroom2= classroomRepository.findById(c1.getUuid()).get();
//            classroom2.setStudents(List.of(saved2, saved3));
//            classroomRepository.save(classroom2);
//
//            var classroom3 = classroomRepository.findById(c1.getUuid()).get();
//            classroom3.setStudents(List.of(saved1, saved3));
//            classroomRepository.save(classroom3);
//
//            var classroom4 = classroomRepository.findById(c1.getUuid()).get();
//            classroom4.setStudents(List.of(saved1, saved2));
//            classroomRepository.save(classroom4);

        }
    }
}
