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
@Table(name = "TEACHER")
public class Teacher extends AbstractEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Classroom> classrooms;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Monitor> monitors;

    @ManyToMany
    @JoinTable(name = "teacher_report", joinColumns = @JoinColumn(name = "report_id"), inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private Set<Report> reports;

    public Teacher() {
        super(Instant.now());
        this.classrooms = Collections.emptyList();
        this.monitors = Collections.emptyList();
        this.reports = Collections.emptySet();
    }

    public Teacher(UUID uuid) {
        super(Instant.now(), uuid);
        this.classrooms = Collections.emptyList();
        this.monitors = Collections.emptyList();
        this.reports = Collections.emptySet();
    }
}
