package br.com.imd.pdse.monitorando.domain;

import br.com.imd.pdse.monitorando.domain.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "MONITOR")
public class Monitor extends AbstractEntity {

    @ElementCollection
    @CollectionTable(name="SCHEDULES")
    private Set<Instant> schedules = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "monitor", cascade = CascadeType.ALL)
    private List<Classroom> classroom;

    public Monitor(){
        super(Instant.now());
        schedules = Collections.emptySet();
        classroom = Collections.emptyList();
    }
}
