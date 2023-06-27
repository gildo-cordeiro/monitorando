package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.service.ExerciseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

//    @PostMapping("/exercise")
//    public String save(@Valid @ModelAttribute("exercise") ExerciseDto exerciseDto,
//                       @Valid @ModelAttribute("user") User user,
//                       BindingResult result, Model model){
//        var saved = exerciseService.save(new Exercise(exerciseDto.getTitle(), exerciseDto.getDescription(), exerciseDto.getClassroomId()));
//        if (saved.isPresent()) {
//            model.addAttribute("exercise", saved.get());
//            model.addAttribute("foundUser", user);
//            return "classroom/classroom";
//        }
//        return "classroom/classroom";
//    }

    @PostMapping("/exercise")
    public String save(){
        return "classroom/classroom";
    }
}