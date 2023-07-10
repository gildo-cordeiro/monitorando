package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.domain.Comment;
import br.com.imd.pdse.monitorando.domain.Exercise;
import br.com.imd.pdse.monitorando.domain.Submission;
import br.com.imd.pdse.monitorando.domain.User;
import br.com.imd.pdse.monitorando.service.ExerciseService;
import br.com.imd.pdse.monitorando.service.SubmissionService;
import br.com.imd.pdse.monitorando.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class    SubmissionController {

    private final ExerciseService exerciseService;

    private final SubmissionService submissionService;

    private final UserService userService;

    public SubmissionController(ExerciseService exerciseService, SubmissionService submissionService, UserService userService) {
        this.exerciseService = exerciseService;
        this.submissionService = submissionService;
        this.userService = userService;
    }


    @PostMapping("submission/save")
    public String save(@ModelAttribute("submission") Submission submission,
                       HttpServletRequest request) throws Exception {
        var foundUser = (User) request.getSession().getAttribute("foundUser");
        var foundExercise = (Exercise) request.getSession().getAttribute("foundExercise");

        submission.setUser(foundUser);
        submission.setExercise(foundExercise);
        submission.setActive(true);

        var savedSub = submissionService.save(submission);
        request.getSession().setAttribute("savedSub", savedSub);
        return "redirect:/exercise/access?id=" + foundExercise.getUuid();
    }

    @PostMapping("submission/comment/save")
    public String saveComment(@RequestParam(name = "uuid") String id,
                              @ModelAttribute("comments") Comment comments) {
        var submission = submissionService.findById(UUID.fromString(id)).get();
        var user = userService.findById(submission.getUser().getUuid());

        comments.setSubmission(submission);
        comments.setUser(user);

        submissionService.save(comments);
        return "redirect:/exercise/access?id=" + submission.getExercise().getUuid();
    }

    @GetMapping("submission/close")
    public String closeSubmission(@RequestParam(name = "id") String id){
        var submission = submissionService.findById(UUID.fromString(id)).get();
        submission.setActive(!submission.isActive());

        submissionService.save(submission);
        return "redirect:/exercise/access?id=" + submission.getExercise().getUuid();
    }
}
