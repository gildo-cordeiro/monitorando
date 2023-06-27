package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.domain.Monitor;
import br.com.imd.pdse.monitorando.domain.User;
import br.com.imd.pdse.monitorando.domain.enums.UserType;
import br.com.imd.pdse.monitorando.repository.MonitorRepository;
import br.com.imd.pdse.monitorando.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(final UserRepository repository, final MonitorRepository monitorRepository,
                       final PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> login(User dto){
        return repository.findByUser(dto.getUsername(), dto.getPassword());
    }

    public User findByUsername(String username){
        return repository.findByUsername(username);
    }

    public Optional<User> save(User dto){
        var foundedUser = repository.findByUser(dto.getUsername(), dto.getPassword());
        var encryptPass = passwordEncoder.encode(dto.getPassword());
        String password = dto.getPassword();

        boolean hasUpperCase = false; // Variável para verificar se a senha possui letras maiúsculas
        boolean hasLowerCase = false; // Variável para verificar se a senha possui letras minúsculas
        boolean hasDigit = false; // Variável para verificar se a senha possui números
        boolean hasSpecialChar = false; // Variável para verificar se a senha possui caracteres especiais

        // Verifica cada caractere da senha para verificar as regras
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            }
            if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            }
            if (Character.isDigit(c)) {
                hasDigit = true;

                // Verifique aqui quais caracteres são considerados como especiais de acordo com suas regras
                // Exemplo: Se considerarmos caracteres especiais como !, @, #, $, %, poderíamos fazer assim:
                if (c == '!' || c == '@' || c == '#' || c == '$' || c == '%') {
                    hasSpecialChar = true;
                }
            }
        }

//        if (!hasUpperCase || !hasLowerCase || !hasDigit || !hasSpecialChar) {
//            model.addAttribute("user", user);
//            model.addAttribute("error", "A senha deve conter letras maiúsculas, letras minúsculas, números e caracteres especiais.");
//            return REGISTER_PAGE;
//        }

        if (foundedUser.isEmpty()) {
            if(dto.getUserType().equals(UserType.MONITOR)){
                var user = new User(dto.getName(), dto.getUsername(), encryptPass, dto.getUserType(), new Monitor());
                var savedUser = repository.save(user);

                return Optional.of(foundedUser.orElse(savedUser));
            } else if (dto.getUserType().equals(UserType.ALUNO)){
                var user = new User(dto.getName(), dto.getUsername(), encryptPass, dto.getUserType(), new Monitor());
                var savedUser = repository.save(user);

                return Optional.of(foundedUser.orElse(savedUser));
            }else if (dto.getUserType().equals(UserType.PROFESSOR)){
                var user = new User(dto.getName(), dto.getUsername(), encryptPass, dto.getUserType(), new Monitor());
                var savedUser = repository.save(user);

                return Optional.of(foundedUser.orElse(savedUser));
            }
        }
        return Optional.empty();
    }
}
