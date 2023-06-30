package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.domain.Classroom;
import br.com.imd.pdse.monitorando.domain.User;
import br.com.imd.pdse.monitorando.service.ExerciseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }


    @PostMapping("exercise/save")
    public String save(){
        return "exercise";
    }

    @GetMapping("exercise")
    public String load(@ModelAttribute("classroom") Classroom classroom, Model model){
        model.addAttribute("classroom", classroom);
        return "exercise";
    }
}