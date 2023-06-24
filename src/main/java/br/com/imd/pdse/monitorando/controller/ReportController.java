package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalTime;

@Controller
public class ReportController {

    private static final String REPORT_PAGE = "report/report";

    ClassroomController classroomController = new ClassroomController();

    @GetMapping("report/report")
    public String report(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("time", classroomController.getGreeting());
        return REPORT_PAGE;
    }
}