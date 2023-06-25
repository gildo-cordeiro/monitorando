package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.domain.Exercise;
import br.com.imd.pdse.monitorando.domain.User;
import br.com.imd.pdse.monitorando.domain.dto.ExerciseDto;
import br.com.imd.pdse.monitorando.domain.dto.UserDto;
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

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new UserDto());
        return LOGIN_PAGE;
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new UserDto());
        return REGISTER_PAGE;
    }

    @PostMapping("/process-login")
    public String login(@Valid @ModelAttribute("user") UserDto user, BindingResult result, Model model) {
        User foundUser = service.findByEmail(user.getLogin());
        model.addAttribute("foundUser", foundUser);
        model.addAttribute("exercise", new Exercise());
        return "classroom/classroom";
    }

    @PostMapping("/save")
    public String register(@Valid @ModelAttribute("user") UserDto user, Model model) {
        var savedUser = service.save(user);
        if (savedUser.isPresent()) {
            model.addAttribute("user", savedUser.get());
            return LOGIN_PAGE;
        }

        model.addAttribute("user", new UserDto());
        return REGISTER_PAGE;
    }
}