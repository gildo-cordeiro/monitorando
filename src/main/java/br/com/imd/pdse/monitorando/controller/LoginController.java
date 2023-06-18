package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.domain.Professor;
import br.com.imd.pdse.monitorando.service.ProfessorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
public class LoginController {

    private static final String LOGIN_PAGE = "login";

    private final ProfessorService service;

    public LoginController(final ProfessorService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String login() {
        return LOGIN_PAGE;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(){
        Professor professor = new Professor("Gildo", "gildo123", "123");
        service.cadastrar(professor);
        return LOGIN_PAGE;
    }
}