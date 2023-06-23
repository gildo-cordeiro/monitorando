package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.domain.User;
import br.com.imd.pdse.monitorando.domain.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    private static final String HOME_PAGE = "home";

    @GetMapping("/home")
    public String home(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", user);
        return HOME_PAGE;
    }
}