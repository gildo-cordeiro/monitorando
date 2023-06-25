package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalTime;

@Controller
public class ClassroomController {

    private static final String CLASSROOM_PAGE = "classroom/classroom";

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
}