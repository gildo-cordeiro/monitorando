package br.com.imd.pdse.monitorando.domain;

import br.com.imd.pdse.monitorando.domain.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "SUBMISSION")
public class Submission extends AbstractEntity {

    @Column(name = "ANSWER", nullable = false)
    private String answer;
    @Column(name = "PRIVACY", columnDefinition = "boolean default true")
    private boolean privacy;
    @Column(name = "ACTIVE", columnDefinition = "boolean default true")
    private boolean active;
    @ManyToOne
    private Exercise exercise;
    @ManyToOne
    private User user;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "submission", cascade = CascadeType.ALL)
    private List<Comment> comment;

    public Submission(){
        super(Instant.now());
    }
}
