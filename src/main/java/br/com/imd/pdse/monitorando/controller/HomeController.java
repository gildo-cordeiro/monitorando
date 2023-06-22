package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.domain.Professor;
import br.com.imd.pdse.monitorando.service.ProfessorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private static final String HOME_PAGE = "home";

    private final ProfessorService service;

    public HomeController(final ProfessorService service) {
        this.service = service;
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("titulo", "Home");
        return HOME_PAGE;
    }
}