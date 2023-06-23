package br.com.imd.pdse.monitorando.domain;

import br.com.imd.pdse.monitorando.domain.enums.UserType;
import br.com.imd.pdse.monitorando.domain.generic.AbstractEntity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "USER_TABLE")
public class User extends AbstractEntity{

    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "LOGIN", nullable = false)
    private String login;
    @Column(name = "PASS", nullable = false)
    private String pass;
    @Column(name = "USER_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MONITOR_UUID", referencedColumnName = "UUID")
    private Monitor monitor;

    public User(final String name, final String login, final String pass, final UserType userType, final Monitor monitor) {
        super(Instant.now());
        this.name = name;
        this.login = login;
        this.pass = pass;
        this.userType = userType;
        this.monitor = monitor;
    }

    public User(){
        super(Instant.now());
    }
}
