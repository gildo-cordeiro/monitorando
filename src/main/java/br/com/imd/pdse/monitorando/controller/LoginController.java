package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.domain.Exercise;
import br.com.imd.pdse.monitorando.domain.User;
import br.com.imd.pdse.monitorando.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private static final String LOGIN_PAGE = "login";

    private static final String REGISTER_PAGE = "register";

    private final UserService service;

    public LoginController(final UserService service) {
        this.service = service;
    }

    @GetMapping({"/login", "/"})
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        return LOGIN_PAGE;
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return REGISTER_PAGE;
    }

    @PostMapping("/process-login")
    public String login(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        var foundUser = service.findByUsername(user.getUsername());
        var classrooms = foundUser.getMonitor().getClassroom();

        if (!foundUser.getMonitor().getClassroom().isEmpty())
            model.addAttribute("classroom", classrooms);

        model.addAttribute("foundUser", foundUser);
//        model.addAttribute("exercise", new Exercise());
        return "classroom";
    }

    @PostMapping("/save")
    public String register(@Valid @ModelAttribute("user") User user, Model model) {
        var savedUser = service.save(user);
        if (savedUser.isPresent()) {
            model.addAttribute("user", savedUser.get());
            return LOGIN_PAGE;
        }

        model.addAttribute("user", new User());
        return REGISTER_PAGE;
    }
}