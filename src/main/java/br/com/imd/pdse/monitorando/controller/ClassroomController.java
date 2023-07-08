package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.domain.*;
import br.com.imd.pdse.monitorando.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ClassroomController {

    private final ClassroomService classroomService;
    private final MonitorService monitorService;
    private final UserService service;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final StudentTeacherService studentTeacherService;


    public ClassroomController(ClassroomService classroomService, MonitorService monitorService, UserService service, TeacherService teacherService, StudentService studentService, StudentTeacherService studentTeacherService) {
        this.classroomService = classroomService;
        this.monitorService = monitorService;
        this.service = service;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.studentTeacherService = studentTeacherService;
    }

    @GetMapping("/classroom")
    public String classroom(HttpServletRequest request, Model model) {
        var foundUser = (User) request.getSession().getAttribute("foundUser");
        var classrooms = service.getClassroomList(foundUser.getUserType(), foundUser.getUuid());
        var monitors = monitorService.monitorList();
        var teacher = teacherService.findTeacherByUserId(foundUser.getUuid());

        Classroom classroom = new Classroom();
        classroom.setTeacher(teacher);

        model.addAttribute("classroom", classroom);
        model.addAttribute("monitors", monitors);
        model.addAttribute("classrooms", classrooms);
        model.addAttribute("foundUser", foundUser);
        model.addAttribute("teacher", teacher);
        return "classroom";
    }

    @GetMapping("classroom/access")
    public String access(@RequestParam(name = "id") String id, @ModelAttribute("exercise") Exercise exercise, Model model) {
        var classroomFound = classroomService.findById(UUID.fromString(id));
        exercise.setClassroom(classroomFound);
        List<Exercise> exerciseList = new ArrayList<>();

        classroomFound.getExercise().forEach(exercise1 -> {
            if (!exercise1.isActive()) exerciseList.add(exercise1);
        });

        classroomFound.getExercise().removeAll(exerciseList);

        model.addAttribute("classroomFound", classroomFound);
        return "exercise";
    }

//    @GetMapping("classroom/update")
//    public String update(@RequestParam(name = "id") String id, Model model) throws Exception {
//        var classroom = classroomService.findById(UUID.fromString(id));
//        model.addAttribute("classroom", classroom);
//        return "exercise";
//    }


    @GetMapping("classroom/remove")
    public String remove(@RequestParam(name = "id") String id, @ModelAttribute("classroom") Classroom classroom, Model model, HttpServletRequest request) {
        var classFound = classroomService.findById(UUID.fromString(id));
        classroomService.softDelete(classFound);

        var foundUser = (User) request.getSession().getAttribute("foundUser");
        var classrooms = service.getClassroomList(foundUser.getUserType(), foundUser.getUuid());

        model.addAttribute("classrooms", classrooms);
        return "redirect:/classroom";
    }


    @PostMapping("classroom/save")
    public String save(@ModelAttribute("classroom") Classroom classroom, Model model) throws Exception {
        var savedMonitor = monitorService.findById(classroom.getMonitor().getUuid());
        classroom.setActive(true);
        classroom.setMonitor(savedMonitor);

        classroomService.save(classroom);

        var monitors = monitorService.monitorList();

        model.addAttribute("monitors", monitors);

        return "redirect:/classroom";
    }

    @GetMapping("classroom/student-classroom/access")
    public String accessStudents(@RequestParam(name = "id") String id,
                                 @RequestParam(defaultValue = "1", name = "page") int page,
                                 @RequestParam(defaultValue = "3", name = "size") int size,
                                 @RequestParam(defaultValue = "", name = "student.uuid", required = false) List<String> checkBoxValues,
                                 Model model) {
        try {
            var classFound = classroomService.findById(UUID.fromString(id));
            var paging = PageRequest.of(page - 1, size);
            var students = studentService.findStudentsThatNotInClassroom(classFound.getUuid(), paging);

            StudentClassroom studentClassroom = new StudentClassroom();
            studentClassroom.setClassroom(classFound);

            var pageStudent = students.getContent();

            model.addAttribute("checkBoxValues", checkBoxValues);
            model.addAttribute("pageStudent", pageStudent);
            model.addAttribute("currentPage", students.getNumber() + 1);
            model.addAttribute("totalItems", students.getTotalElements());
            model.addAttribute("totalPages", students.getTotalPages());
            model.addAttribute("pageSize", size);
            model.addAttribute("page", page);
            model.addAttribute("studentClassroom", studentClassroom);
            model.addAttribute("classFound", classFound);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "student";
    }

    @PostMapping("classroom/student-classroom/save")
    public String saveStudentTeacher(@ModelAttribute("studentClassroom") StudentClassroom studentClassroom, @RequestParam("student.uuid") List<String> checkBoxValues) {
        var teacher = classroomService.findById(studentClassroom.getClassroom().getUuid());

        checkBoxValues.forEach(s -> {
            StudentClassroom st = new StudentClassroom();
            var student = studentService.findById(UUID.fromString(s));
            st.setClassroom(teacher);
            st.setStudent(student);

            studentTeacherService.save(st);
        });

        return "redirect:/classroom";
    }
}