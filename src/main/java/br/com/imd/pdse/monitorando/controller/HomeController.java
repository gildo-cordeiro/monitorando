package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.domain.dto.UsuarioDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private static final String HOME_PAGE = "home";

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("username", new UsuarioDto().getNome());
        return HOME_PAGE;
    }
}