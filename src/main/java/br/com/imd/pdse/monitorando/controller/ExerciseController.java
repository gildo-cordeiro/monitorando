package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ExerciseController {

    private static final String EXERCISE_PAGE = "classroom/exercise";

    @GetMapping("classroom/exercise")
    public String exercise(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", new User());
        return EXERCISE_PAGE;
    }
}