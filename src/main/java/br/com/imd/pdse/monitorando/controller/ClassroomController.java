package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.domain.Classroom;
import br.com.imd.pdse.monitorando.domain.User;
import br.com.imd.pdse.monitorando.service.ClassroomService;
import br.com.imd.pdse.monitorando.service.MonitorService;
import br.com.imd.pdse.monitorando.service.TeacherService;
import br.com.imd.pdse.monitorando.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class ClassroomController {

    private final ClassroomService classroomService;
    private final MonitorService monitorService;
    private final UserService service;

    private final TeacherService teacherService;


    public ClassroomController(ClassroomService classroomService, MonitorService monitorService, UserService service, TeacherService teacherService) {
        this.classroomService = classroomService;
        this.monitorService = monitorService;
        this.service = service;
        this.teacherService = teacherService;
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
    public String access(@RequestParam(name = "id") String id, Model model) {
        var classroom = classroomService.findById(UUID.fromString(id));
        model.addAttribute("classroom", classroom);
        return "exercise";
    }

//    @GetMapping("classroom/update")
//    public String update(@RequestParam(name = "id") String id, Model model) throws Exception {
//        var classroom = classroomService.findById(UUID.fromString(id));
//        model.addAttribute("classroom", classroom);
//        return "exercise";
//    }


    @GetMapping("classroom/remove")
    public String remove(@RequestParam(name = "id") String id,
                         @ModelAttribute("classroom") Classroom classroom,
                         Model model, HttpServletRequest request,
                         BindingResult bindingResult) {
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
}