package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.domain.Exercise;
import br.com.imd.pdse.monitorando.domain.Submission;
import br.com.imd.pdse.monitorando.domain.User;
import br.com.imd.pdse.monitorando.service.ClassroomService;
import br.com.imd.pdse.monitorando.service.ExerciseService;
import br.com.imd.pdse.monitorando.service.SubmissionService;
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
    
    private final SubmissionService submissionService;

    public ExerciseController(ExerciseService exerciseService, ClassroomService classroomService, SubmissionService submissionService) {
        this.exerciseService = exerciseService;
        this.classroomService = classroomService;
        this.submissionService = submissionService;
    }

    @GetMapping("exercise/access")
    public String access(@RequestParam(name = "id") String id,
                         @ModelAttribute("exercise") Exercise exercise,
                         Model model,
                         HttpServletRequest request) {
        var foundExercise = exerciseService.findById(UUID.fromString(id));
        var foundUser = (User) request.getSession().getAttribute("foundUser");

        Submission submission = new Submission();
        submission.setExercise(foundExercise);
        submission.setUser(foundUser);

        var activeAccess = submissionService.submissionAccess(submission, request);

        model.addAttribute("activeAccess", activeAccess);
        model.addAttribute("exercise", foundExercise);
        model.addAttribute("user", foundUser);
        model.addAttribute("submission", submission);
        return "submission";
    }

    @GetMapping("exercise/remove")
    public String remove(@RequestParam(name = "id") String id,
                         @ModelAttribute("exercise") Exercise exercise) {
        var exerciseFound = exerciseService.findById(UUID.fromString(id));
        var foundClass = classroomService.findById(exerciseFound.getClassroom().getUuid());
        exerciseFound.setClassroom(foundClass);

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