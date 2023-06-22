package br.com.imd.pdse.monitorando.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopicController {

    private static final String TOPIC_PAGE = "forum/topic";

    @GetMapping("/topic")
    public String topic(Model model) {
        model.addAttribute("titulo", "Topic");
        return TOPIC_PAGE;
    }
}