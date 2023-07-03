package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.domain.Exercise;
import br.com.imd.pdse.monitorando.domain.Submission;
import br.com.imd.pdse.monitorando.domain.Topic;
import br.com.imd.pdse.monitorando.domain.User;
import br.com.imd.pdse.monitorando.service.TopicService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.UUID;

@Controller
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/forum")
    public String forum(Model model, HttpServletRequest request) {
        var topics = topicService.findAll();
        var user = (User) request.getSession().getAttribute("foundUser");

        Topic topic = new Topic();
        topic.setUser(user);

        model.addAttribute("topic", topic);
        model.addAttribute("topics", topics);
        return "forum";
    }

    @GetMapping("topic/access")
    public String access(@RequestParam(name = "id") String id,
                         Model model,
                         HttpServletRequest request) {
        var foundTopic = topicService.findById(UUID.fromString(id));
        var foundUser = (User) request.getSession().getAttribute("foundUser");

        Topic topic = new Topic();
        topic.setUser(foundUser);

        model.addAttribute("user", foundUser);
        model.addAttribute("topic", foundTopic);
        return "topic";
    }

    @PostMapping("topic/save")
    public String topic(@ModelAttribute("topic") Topic topic) {
        topic.setOpen(true);
        topic.setActive(true);

        topicService.save(topic);
        return "redirect:/forum";
    }
}