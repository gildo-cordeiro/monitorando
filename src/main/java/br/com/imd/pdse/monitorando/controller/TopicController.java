package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalTime;

@Controller
public class TopicController {

    private static final String TOPIC_PAGE = "report/topic";

    ClassroomController classroomController = new ClassroomController();

    @GetMapping("report/topic")
    public String topic(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("time", classroomController.getGreeting());
        return TOPIC_PAGE;
    }
}