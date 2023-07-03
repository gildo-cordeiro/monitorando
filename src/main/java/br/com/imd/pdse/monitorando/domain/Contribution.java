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
@Table(name = "CONTRIBUTION")
public class Contribution extends AbstractEntity {

    @Column(name = "ANSWER")
    private String answer;

    @ManyToOne
    private Topic topic;

    @ManyToOne
    private User user;

    public Contribution() {
        super(Instant.now());
    }
}
