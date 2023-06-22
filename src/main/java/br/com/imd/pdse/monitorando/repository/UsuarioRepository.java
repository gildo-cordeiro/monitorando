package br.com.imd.pdse.monitorando.repository;

import br.com.imd.pdse.monitorando.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {


    @Query(value = "SELECT * FROM Usuario u WHERE u.login = :login and u.senha = :senha", nativeQuery = true)
    Optional<Usuario> findByUser(@Param("login") String login, @Param("senha") String senha);

}
