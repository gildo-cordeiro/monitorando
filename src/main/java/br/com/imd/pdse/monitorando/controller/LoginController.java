package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.domain.Classroom;
import br.com.imd.pdse.monitorando.domain.User;
import br.com.imd.pdse.monitorando.domain.dto.UserDto;
import br.com.imd.pdse.monitorando.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class LoginController {

    private static final String LOGIN_PAGE = "login";

    private static final String REGISTER_PAGE = "register";

    private final UserService service;

    public LoginController(final UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String loginPage(Model model) {
        model.addAttribute("user", new UserDto());
        return LOGIN_PAGE;
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("user", new UserDto());
        return REGISTER_PAGE;
    }

    @PostMapping("/login")
    public RedirectView login(@ModelAttribute("user") UserDto user, RedirectAttributes redirectAttrs) {
        Optional<User> foundUser = service.login(user);

        if (foundUser.isPresent()){
            redirectAttrs.addFlashAttribute("user", foundUser.get());
            return new RedirectView("/home");
        }
        return new RedirectView(LOGIN_PAGE);
    }

    @PostMapping("/save")
    public String register(@ModelAttribute("user") UserDto user, Model model) {
        var savedUser = service.save(user);

        if (savedUser.isPresent()) {
            model.addAttribute("user", savedUser);
            return LOGIN_PAGE;
        }

        model.addAttribute("user", new UserDto());
        return REGISTER_PAGE;
    }
}