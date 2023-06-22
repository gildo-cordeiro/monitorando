package br.com.imd.pdse.monitorando.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForumController {

    private static final String FORUM_PAGE = "forum/forum";

    @GetMapping("/forum")
    public String forum(Model model) {
        model.addAttribute("titulo", "Forum");
        return FORUM_PAGE;
    }
}