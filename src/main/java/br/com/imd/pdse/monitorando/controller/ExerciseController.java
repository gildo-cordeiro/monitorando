package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.domain.Exercise;
import br.com.imd.pdse.monitorando.domain.User;
import br.com.imd.pdse.monitorando.service.ClassroomService;
import br.com.imd.pdse.monitorando.service.ExerciseService;
import br.com.imd.pdse.monitorando.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class ExerciseController {

    private final ExerciseService exerciseService;

    private final ClassroomService classroomService;

    public ExerciseController(ExerciseService exerciseService, ClassroomService classroomService) {
        this.exerciseService = exerciseService;
        this.classroomService = classroomService;
    }

    @GetMapping("exercise/access/")
    public String access(@RequestParam(name = "id") String id,
                         @ModelAttribute("exercise") Exercise exercise, Model model,
                         BindingResult bindingResult) {
        var foundExercise = exerciseService.findById(UUID.fromString(id));
        model.addAttribute("exercise", foundExercise);
        return "exercise";
    }

    @GetMapping("exercise/remove")
    public String remove(@RequestParam(name = "id") String id,
                         @ModelAttribute("exercise") Exercise exercise,
                         BindingResult bindingResult) {
        var exerciseFound = exerciseService.findById(UUID.fromString(id));
        var foundClass = classroomService.findById(exercise.getClassroom().getUuid());
        exerciseService.softDelete(exerciseFound);

        return "redirect:/classroom/access?id=" + foundClass.getUuid();
    }


    @PostMapping("exercise/save")
    public String save(@ModelAttribute("exercise") Exercise exercise) throws Exception {
        var foundClass = classroomService.findById(exercise.getClassroom().getUuid());

        exercise.setActive(true);
        exercise.setClassroom(foundClass);

        exerciseService.save(exercise);
        return "redirect:/classroom/access?id=" + foundClass.getUuid();
    }
}