package br.com.imd.pdse.monitorando.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

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
