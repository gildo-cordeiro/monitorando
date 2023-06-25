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
@Table(name = "REPORT")
public class Report extends AbstractEntity {

    @Column(name = "REPORT_NAME", nullable = false)
    private String reportName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "report", cascade = CascadeType.ALL)
    private List<Topic> topic;

    @ManyToOne
    private Monitor monitor;

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
