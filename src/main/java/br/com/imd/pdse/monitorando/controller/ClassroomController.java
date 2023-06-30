package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.domain.User;
import br.com.imd.pdse.monitorando.service.AuthenticationService;
import br.com.imd.pdse.monitorando.service.ClassroomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.util.UUID;

@Controller
public class ClassroomController {

    private final ClassroomService classroomService;

    private static final String CLASSROOM_PAGE = "classroom/classroom";

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    public String getGreeting() {
        LocalTime currentTime = LocalTime.now();
        String greeting;

        if (currentTime.isAfter(LocalTime.of(0, 0)) && currentTime.isBefore(LocalTime.of(12, 0))) {
            greeting = "Bom dia";
        } else if (currentTime.isAfter(LocalTime.of(12, 0)) && currentTime.isBefore(LocalTime.of(18, 0))) {
            greeting = "Boa tarde";
        } else {
            greeting = "Boa noite";
        }
        return greeting;
    }

    @GetMapping("/classroom")
    public String classroom(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("time", getGreeting());
        return CLASSROOM_PAGE;
    }

    @GetMapping("classroom/access")
    public String access(@RequestParam(name = "id") String id, Model model) throws Exception {
        var classroom = classroomService.findById(UUID.fromString(id));
        model.addAttribute("classroom", classroom.get());
        return "exercise";
    }
}