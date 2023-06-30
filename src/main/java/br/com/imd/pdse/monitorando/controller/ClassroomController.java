package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.Util;
import br.com.imd.pdse.monitorando.domain.Classroom;
import br.com.imd.pdse.monitorando.domain.Exercise;
import br.com.imd.pdse.monitorando.domain.User;
import br.com.imd.pdse.monitorando.service.ClassroomService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class ClassroomController {

    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping("/classroom")
    public String classroom(HttpServletRequest request, Model model) {
        var foundUser = (User) request.getSession().getAttribute("foundUser");
        var classrooms = classroomService.findAllByTeacherOrMonitor(Util.getUUIDByUserType(foundUser));

        Classroom classroom = new Classroom();
        classroom.setTeacher(foundUser.getTeacher());

        model.addAttribute("classroom", classroom);
        model.addAttribute("exercise", new Exercise());

        model.addAttribute("classrooms", classrooms);
        model.addAttribute("foundUser", foundUser);
        return "classroom";
    }

    @GetMapping("classroom/access")
    public String access(@RequestParam(name = "id") String id, Model model) throws Exception {
        var classroom = classroomService.findById(UUID.fromString(id));
        model.addAttribute("classroom", classroom);
        return "exercise";
    }

    @PostMapping("classroom/save")
    public String save(@ModelAttribute("classroom") Classroom classroom, HttpServletRequest request, Model model) throws Exception {
        classroomService.save(classroom);
        return "redirect:/classroom";
    }
}