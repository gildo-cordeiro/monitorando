package br.com.imd.pdse.monitorando.domain;

import br.com.imd.pdse.monitorando.domain.enums.UserType;
import br.com.imd.pdse.monitorando.domain.generic.AbstractEntity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;

@Entity
@Setter
@Getter
@Table(name = "USER_TABLE")
public class User extends AbstractEntity implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "USERNAME", nullable = false)
    private String username;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "USER_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MONITOR_UUID", referencedColumnName = "UUID")
    private Monitor monitor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TEACHER_UUID", referencedColumnName = "UUID")
    private Teacher teacher;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "STUDENT_UUID", referencedColumnName = "UUID")
    private Student student;

    public User(final String name, final String username, final String password, final UserType userType, final Monitor monitor) {
        super(Instant.now());
        this.name = name;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.monitor = monitor;
    }

    public User(final String name, final String username, final String password, final UserType userType, final Teacher teacher) {
        super(Instant.now());
        this.name = name;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.teacher = teacher;
    }

    public User(final String name, final String username, final String password, final UserType userType, final Student student) {
        super(Instant.now());
        this.name = name;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.student = student;
    }

    public User(){
        super(Instant.now());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(userType.getCode()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
