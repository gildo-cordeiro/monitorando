package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.domain.Submission;
import br.com.imd.pdse.monitorando.domain.User;
import br.com.imd.pdse.monitorando.service.ExerciseService;
import br.com.imd.pdse.monitorando.service.SubmissionService;
import br.com.imd.pdse.monitorando.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SubmissionController {

    private final ExerciseService exerciseService;

    private final SubmissionService submissionService;

    private final UserService userService;

    public SubmissionController(ExerciseService exerciseService, SubmissionService submissionService, UserService userService) {
        this.exerciseService = exerciseService;
        this.submissionService = submissionService;
        this.userService = userService;
    }


    @PostMapping("submission/save")
    public String save(@ModelAttribute("submission") Submission submission) throws Exception {
        var foundExercise = exerciseService.findById(submission.getExercise().getUuid());
        var foundUser = userService.findById(submission.getUser().getUuid());

        submission.setUser(foundUser);
        submission.setExercise(foundExercise);
        submission.setActive(true);

        submissionService.save(submission);
        return "redirect:/exercise/access?id=" + foundExercise.getUuid();
    }
}
