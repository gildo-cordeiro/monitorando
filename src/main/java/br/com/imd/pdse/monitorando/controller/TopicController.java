package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.domain.*;
import br.com.imd.pdse.monitorando.service.TopicService;
import br.com.imd.pdse.monitorando.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.UUID;

@Controller
public class TopicController {

    private final TopicService topicService;
    private final UserService userService;


    public TopicController(TopicService topicService, UserService userService) {
        this.topicService = topicService;
        this.userService = userService;
    }

    @GetMapping("/forum")
    public String forum(Model model, HttpServletRequest request) {
        var topics = topicService.getAllTopicsOrderedByLikes();
        var user = (User) request.getSession().getAttribute("foundUser");

        for (Topic topic : topics) {
            int contributionCount = topicService.countContributionsByTopicId(topic.getUuid());
            topic.setContributionCount(contributionCount);
        }

        Topic topic = new Topic();
        topic.setUser(user);

        model.addAttribute("topic", topic);
        model.addAttribute("topics", topics);
        return "forum";
    }

    @GetMapping("topic/like")
    public String updateLikes(@RequestParam(name = "id") String id, HttpServletRequest request){
        var topic = topicService.findById(UUID.fromString(id));
        var user = (User) request.getSession().getAttribute("foundUser");

        if (topic.getUser().getUuid() == user.getUuid())
            topic.setLikes(topic.getLikes() - 1);

        topic.setLikes(topic.getLikes() + 1);

        topicService.save(topic);
        return "redirect:/forum";
    }

    @GetMapping("topic/fixed")
    public String fixed(@RequestParam(name = "id") String id){
        var topic = topicService.findById(UUID.fromString(id));
        topic.setFixed(!topic.isFixed());

        topicService.save(topic);
        return "redirect:/forum";
    }

    @GetMapping("topic/remove")
    public String remove(@RequestParam(name = "id") String id){
        var topic = topicService.findById(UUID.fromString(id));
        topic.setActive(false);

        topicService.save(topic);
        return "redirect:/forum";
    }

    @GetMapping("topic/close")
    public String close(@RequestParam(name = "id") String id){
        var topic = topicService.findById(UUID.fromString(id));
        topic.setOpen(!topic.isOpen());
        topic.setClosedDate(topic.getClosedDate() == null ? LocalDate.now() : null);

        topicService.save(topic);
        return "redirect:/forum";
    }

    @GetMapping("topic/access")
    public String access(@RequestParam(name = "id") String id,
                               Model model,
                               HttpServletRequest request) {

        var foundTopic = topicService.findById(UUID.fromString(id));
        var foundUser = (User) request.getSession().getAttribute("foundUser");
        var contributions = topicService.findAllContributions();

        Topic topic = new Topic();
        Contribution contribution = new Contribution();

        topic.setUser(foundUser);
        contribution.setTopic(foundTopic);
        contribution.setUser(foundUser);

        model.addAttribute("user", foundUser);
        model.addAttribute("topic", foundTopic);
        model.addAttribute("contribution", contribution);
        model.addAttribute("contributions", contributions);

        return "topic";
    }

    @PostMapping("topic/contribution/save")
    public String save(@ModelAttribute("contribution") Contribution contribution) {
        var foundUser = userService.findById(contribution.getUser().getUuid());
        var foundTopic = topicService.findById(contribution.getTopic().getUuid());

        contribution.setUser(foundUser);
        contribution.setTopic(foundTopic);

        topicService.save(contribution);
        return "redirect:/topic/access?id=" + foundTopic.getUuid();
    }

    @PostMapping("topic/save")
    public String topic(@ModelAttribute("topic") Topic topic) {
        topic.setOpen(true);
        topic.setActive(true);

        topicService.save(topic);
        return "redirect:/forum";
    }
}