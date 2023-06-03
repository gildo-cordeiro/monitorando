package br.com.imd.pdse.monitorando.domain;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Usuario {

    @Id
    protected UUID id;
    @NonNull
    protected String nome;
    @NonNull
    protected String login;
    @NonNull
    protected String senha;

}
