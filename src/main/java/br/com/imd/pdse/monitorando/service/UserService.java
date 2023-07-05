package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.domain.*;
import br.com.imd.pdse.monitorando.domain.enums.UserType;
import br.com.imd.pdse.monitorando.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MonitorService monitorService;
    private final StudentService studentService;
    private final TeacherService teacherService;

    public UserService(final UserRepository userRepository, final PasswordEncoder passwordEncoder, MonitorService monitorService, StudentService studentService, TeacherService teacherService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.monitorService = monitorService;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> save(User dto) {
        var foundedUser = userRepository.findByUser(dto.getUsername(), dto.getPassword());
        var encryptPass = passwordEncoder.encode(dto.getPassword());
        var user = new User(dto.getName(), dto.getUsername(), encryptPass, dto.getUserType());
        User savedUser = null;

        if (foundedUser.isEmpty()) {
            if (dto.getUserType().equals(UserType.MONITOR)) {
                savedUser = userRepository.save(user);
                monitorService.save(new Monitor(savedUser));

            } else if (dto.getUserType().equals(UserType.STUDENT)) {
                savedUser = userRepository.save(user);
                studentService.save(new Student(savedUser));

            } else if (dto.getUserType().equals(UserType.TEACHER)) {
                savedUser = userRepository.save(user);
                teacherService.save(new Teacher(savedUser));
            }
        }
        return Optional.of(foundedUser.orElse(savedUser));
    }

    public List<Classroom> getClassroomList(UserType userType, UUID userId){
        List<Classroom> classrooms = new ArrayList<>();

        if (userType.equals(UserType.MONITOR))
            classrooms.addAll(userRepository.findByIdAndUserTypeMonitor(userType, userId));

        if (userType.equals(UserType.TEACHER))
            classrooms.addAll(userRepository.findByIdAndUserTypeTeacher(userType, userId));

        if (userType.equals(UserType.STUDENT ))
            classrooms.addAll(userRepository.findByIdAndUserTypeStudent(userType, userId));

        return classrooms;
    }

    public User findById(UUID id) {
        if (Objects.isNull(id))
            return null;

        var classroom = userRepository.findById(id);

        return classroom.orElse(null);

    }
}
