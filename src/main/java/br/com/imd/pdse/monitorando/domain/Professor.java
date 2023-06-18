package br.com.imd.pdse.monitorando.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.UUID;


@Entity
@NoArgsConstructor
@Table(name = "PROFESSOR")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private UUID id;
    @NonNull
    @Column(name = "NOME", nullable = false)
    private String nome;
    @NonNull
    @Column(name = "LOGIN", nullable = false)
    private String login;
    @NonNull
    @Column(name = "SENHA", nullable = false)
    private String senha;

    public UUID getId() {
        return id;
    }

    @NonNull
    public String getNome() {
        return nome;
    }

    @NonNull
    public String getLogin() {
        return login;
    }

    @NonNull
    public String getSenha() {
        return senha;
    }

    public Professor(@NonNull String nome, @NonNull String login, @NonNull String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }
}
