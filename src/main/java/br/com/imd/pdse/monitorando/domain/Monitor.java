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
@Table(name = "MONITOR")
public class Monitor extends AbstractEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @ElementCollection
    @CollectionTable(name = "SCHEDULES")
    private Set<Instant> schedules;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "monitor", cascade = CascadeType.ALL)
    private List<Classroom> classroom;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "monitor", cascade = CascadeType.ALL)
    private List<Report> report;

    @ManyToOne
    private Teacher teacher;

    @OneToOne
    @JoinColumn(name = "USER_UUID", referencedColumnName = "UUID")
    private User user;

    public Monitor(UUID uuid, User user) {
        super(Instant.now(), uuid);
        this.user = user;
    }

    public Monitor(Teacher teacher, User user) {
        super(Instant.now());
        this.teacher = teacher;
        this.user = user;
    }

    public Monitor(User user) {
        super(Instant.now());
        this.teacher = teacher;
        this.user = user;
    }

    public Monitor(UUID uuid) {
        super(Instant.now(), uuid);
        schedules = Collections.emptySet();
        classroom = Collections.emptyList();
        report = Collections.emptyList();
    }

    public Monitor(){

    }

}
