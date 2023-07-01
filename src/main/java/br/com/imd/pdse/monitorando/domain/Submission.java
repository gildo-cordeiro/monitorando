package br.com.imd.pdse.monitorando.domain;

import br.com.imd.pdse.monitorando.domain.generic.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Setter
@Getter
@Table(name = "SUBMISSION")
public class Submission extends AbstractEntity {
    @Column(name = "COMMENT", nullable = false)
    private String comment;
    @Column(name = "PRIVACY", columnDefinition = "boolean default true")
    private boolean privacy;
    @Column(name = "ACTIVE", columnDefinition = "boolean default true")
    private boolean active;
    @ManyToOne
    private Exercise exercise;
    @ManyToOne
    private User user;

    public Submission(){
        super(Instant.now());
    }
}
