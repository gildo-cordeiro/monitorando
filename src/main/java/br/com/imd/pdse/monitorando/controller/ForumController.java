package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.domain.Professor;
import br.com.imd.pdse.monitorando.service.ProfessorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ForumController {

    private static final String FORUM_PAGE = "forum/forum";

    private final ProfessorService service;

    public ForumController(final ProfessorService service) {
        this.service = service;
    }

    @GetMapping("/forum")
    public String forum(Model model) {
        model.addAttribute("titulo", "Forum");
        return FORUM_PAGE;
    }
}