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
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "CLASSROOM")
public class Classroom extends AbstractEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "CLASS_NAME", nullable = false)
    private String className;

    @Column(name = "ACTIVE", columnDefinition = "boolean default true")
    private boolean active;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "classroom", cascade = CascadeType.ALL)
    private List<Exercise> exercise;

    @ManyToOne
    private Monitor monitor;

    @ManyToOne
    private Teacher teacher;

    @OneToMany(mappedBy = "classroom")
    private Set<StudentClassroom> classrooms;

    public Classroom(String className, boolean active, Monitor monitor, Teacher teacher) {
        super(Instant.now());
        this.className = className;
        this.active = active;
        this.monitor = monitor;
        this.teacher = teacher;
    }

    public Classroom() {
        super(Instant.now());
        this.exercise = Collections.emptyList();
    }
}
