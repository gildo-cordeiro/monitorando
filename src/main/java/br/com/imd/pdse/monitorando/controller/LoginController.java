package br.com.imd.pdse.monitorando.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private static final String LOGIN_PAGE = "login";

    @GetMapping("/")
    public String login() {
        return LOGIN_PAGE;
    }

    @PostMapping
    public String cadastrar(){

        return "";
    }
}