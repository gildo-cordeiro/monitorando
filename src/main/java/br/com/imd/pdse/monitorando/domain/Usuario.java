package br.com.imd.pdse.monitorando.domain;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "USUARIO")
public class Usuario extends AbstractEntity{

    @Column(name = "NOME", nullable = false)
    private String nome;
    @Column(name = "LOGIN", nullable = false)
    private String login;
    @Column(name = "SENHA", nullable = false)
    private String senha;
    @Column(name = "TIPO_USUARIO", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    public Usuario(final String nome, final String login, final String senha, final TipoUsuario tipoUsuario) {
        super(Instant.now());
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario(){}

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
}
