package br.com.imd.pdse.monitorando.domain;

import br.com.imd.pdse.monitorando.domain.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "CLASSROOM")
public class Classroom extends AbstractEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "CLASS_NAME", nullable = false)
    private String className;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "classroom", cascade = CascadeType.ALL)
    private List<Exercise> exercise;

    @ManyToOne
    private Monitor monitor;

    @ManyToOne
    private Teacher teacher;

    @ManyToMany(mappedBy = "classrooms")
    private List<Student> students;

    public Classroom(String className, Monitor monitor) {
        super(Instant.now());
        this.className = className;
        this.monitor = monitor;
    }

    public Classroom(String className, Teacher teacher) {
        super(Instant.now());
        this.className = className;
        this.teacher = teacher;
    }

    public Classroom() {
        super(Instant.now());
        this.exercise = Collections.emptyList();
    }
}
