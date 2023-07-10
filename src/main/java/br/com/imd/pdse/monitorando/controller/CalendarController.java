package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.domain.Calendar;
import br.com.imd.pdse.monitorando.domain.User;
import br.com.imd.pdse.monitorando.service.TeacherService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class CalendarController {

    private final TeacherService teacherService;

    public CalendarController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @GetMapping("/calendar")
    public String calendar(Model model, HttpServletRequest request) {
        var foundUser = (User) request.getSession().getAttribute("foundUser");
        var teacher = teacherService.findTeacherByUserId(foundUser.getUuid());
        Calendar calendar = new Calendar();
        calendar.setTeacher(teacher);

        model.addAttribute("calendar", calendar);
        return "calendar";
    }

    @PostMapping("/calendar/save")
    public String save(@ModelAttribute("calendar") Calendar calendar) throws Exception {
        var teacher = teacherService.findById(calendar.getTeacher().getUuid());
        calendar.setTeacher(teacher);

        teacherService.saveCalendar(calendar);
        return "redirect:/calendar";
    }
}
