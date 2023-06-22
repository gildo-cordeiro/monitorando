package br.com.imd.pdse.monitorando.controller;

import br.com.imd.pdse.monitorando.domain.Usuario;
import br.com.imd.pdse.monitorando.domain.dto.UsuarioDto;
import br.com.imd.pdse.monitorando.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class LoginController {

    private static final String LOGIN_PAGE = "login";

    private static final String CADASTRO_PAGE = "cadastro";

    private final UsuarioService service;

    public LoginController(final UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String loginPage(Model model) {
        model.addAttribute("user", new UsuarioDto());
        return LOGIN_PAGE;
    }

    @GetMapping("/cadastrar")
    public String cadastrarPage(Model model){
        model.addAttribute("user", new UsuarioDto());
        return CADASTRO_PAGE;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") UsuarioDto user, Model model) {
        Optional<Usuario> usuario = service.findByUser(user);

        if (usuario.isPresent()){
            model.addAttribute("user", usuario);
            return "redirect:/home";
        }

        model.addAttribute("user", new Usuario());
        return LOGIN_PAGE;
    }

    @PostMapping("/save")
    public String cadastrar(@ModelAttribute("user") UsuarioDto user, Model model) {
        var savedUser = service.save(user);

        if (savedUser.isPresent()) {
            model.addAttribute("user", savedUser);
            return LOGIN_PAGE;
        }

        model.addAttribute("user", new UsuarioDto());
        return CADASTRO_PAGE;
    }
}