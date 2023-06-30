package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.domain.*;
import br.com.imd.pdse.monitorando.domain.enums.UserType;
import br.com.imd.pdse.monitorando.repository.MonitorRepository;
import br.com.imd.pdse.monitorando.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
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

        if (foundedUser.isEmpty()) {
            if(dto.getUserType().equals(UserType.MONITOR)){
                var user = new User(dto.getName(), dto.getUsername(), encryptPass, dto.getUserType(), new Monitor());
                var savedUser = repository.save(user);

                return Optional.of(foundedUser.orElse(savedUser));
            } else if (dto.getUserType().equals(UserType.STUDENT)){
                var user = new User(dto.getName(), dto.getUsername(), encryptPass, dto.getUserType(), new Student());
                var savedUser = repository.save(user);

                return Optional.of(foundedUser.orElse(savedUser));
            }else if (dto.getUserType().equals(UserType.TEACHER)){
                var user = new User(dto.getName(), dto.getUsername(), encryptPass, dto.getUserType(), new Teacher());
                var savedUser = repository.save(user);

                return Optional.of(foundedUser.orElse(savedUser));
            }
        }
        return Optional.empty();
    }
}
