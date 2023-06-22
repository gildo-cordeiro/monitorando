package br.com.imd.pdse.monitorando.service;

import br.com.imd.pdse.monitorando.domain.Usuario;
import br.com.imd.pdse.monitorando.domain.dto.UsuarioDto;
import br.com.imd.pdse.monitorando.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(final UsuarioRepository repository) {
        this.repository = repository;
    }

    public Optional<Usuario> findByUser(UsuarioDto dto){
        return repository.findByUser(dto.getLogin(), dto.getSenha());
    }

    public Optional<Usuario> save(UsuarioDto dto){
        var foundedUser = repository.findByUser(dto.getLogin(), dto.getSenha());
        var user = new Usuario(dto.getNome(), dto.getLogin(), dto.getSenha(), dto.getTipoUsuario());

        return Optional.of(foundedUser.orElseGet(() -> repository.save(user)));
    }
}
