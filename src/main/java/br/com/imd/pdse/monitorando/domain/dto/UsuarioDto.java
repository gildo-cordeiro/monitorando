package br.com.imd.pdse.monitorando.domain.dto;

import br.com.imd.pdse.monitorando.domain.TipoUsuario;

public class UsuarioDto {

    private String nome;

    private String login;

    private String senha;

    private TipoUsuario tipoUsuario;


    public String getLogin() {
        return login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
