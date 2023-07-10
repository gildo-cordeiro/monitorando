package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.domain.Classroom;
import br.com.imd.pdse.monitorando.domain.Comment;
import br.com.imd.pdse.monitorando.domain.Submission;
import br.com.imd.pdse.monitorando.service.ExerciseService;
import br.com.imd.pdse.monitorando.service.SubmissionService;
import br.com.imd.pdse.monitorando.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Calendar;

@Controller
public class CalendarController {
    @GetMapping("/calendar")
    public String calendar(Model model) {
        return "calendar";
    }

    @GetMapping("/calendar/save")
    public String save(@ModelAttribute("classroom") Calendar calendar, Model model) throws Exception {

        return "redirect:/calendar";
    }
}
