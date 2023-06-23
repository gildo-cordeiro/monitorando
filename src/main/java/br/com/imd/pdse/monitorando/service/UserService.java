package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.domain.Monitor;
import br.com.imd.pdse.monitorando.domain.User;
import br.com.imd.pdse.monitorando.domain.dto.UserDto;
import br.com.imd.pdse.monitorando.domain.enums.UserType;
import br.com.imd.pdse.monitorando.repository.MonitorRepository;
import br.com.imd.pdse.monitorando.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final MonitorRepository monitorRepository;

    public UserService(final UserRepository repository, final MonitorRepository monitorRepository) {
        this.repository = repository;
        this.monitorRepository = monitorRepository;
    }

    public Optional<User> login(UserDto dto){
        return repository.findByUser(dto.getLogin(), dto.getPass());
    }

    public Optional<User> save(UserDto dto){
        var foundedUser = repository.findByUser(dto.getLogin(), dto.getPass());

        if (foundedUser.isEmpty()) {
            if(dto.getUserType().equals(UserType.MONITOR)){
                var user = new User(dto.getName(), dto.getLogin(), dto.getPass(), dto.getUserType(), new Monitor());
                var savedUser = repository.save(user);

                return Optional.of(foundedUser.orElse(savedUser));
            }
        }
        return Optional.empty();
    }
}
