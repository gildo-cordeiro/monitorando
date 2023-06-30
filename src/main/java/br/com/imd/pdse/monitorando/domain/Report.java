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
@Table(name = "REPORT")
public class Report extends AbstractEntity {

    @Serial
    private static final long serialVersionUID = 1L;
    @Column(name = "REPORT_NAME", nullable = false)
    private String reportName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "report", cascade = CascadeType.ALL)
    private List<Topic> topic;

    @ManyToOne
    private Monitor monitor;

    @ManyToMany(mappedBy = "reports")
    Set<Teacher> teachers;

    public Report(String reportName, Monitor monitor) {
        super(Instant.now());
        this.reportName = reportName;
        this.monitor = monitor;
    }

    public Report() {
        super(Instant.now());
        this.topic = Collections.emptyList();
    }
}
