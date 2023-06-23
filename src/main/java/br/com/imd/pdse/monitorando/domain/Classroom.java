package br.com.imd.pdse.monitorando.domain;

import br.com.imd.pdse.monitorando.domain.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "CLASSROOM")
public class Classroom extends AbstractEntity {

    @Column(name = "CLASS_NAME", nullable = false)
    private String className;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "classroom", cascade = CascadeType.ALL)
    private List<Exercise> exercise;

    @ManyToOne
    private Monitor monitor;

    public Classroom(String className, Monitor monitor) {
        super(Instant.now());
        this.className = className;
        this.monitor = monitor;
    }

    public Classroom() {
        super(Instant.now());
        this.exercise = Collections.emptyList();
    }
}
