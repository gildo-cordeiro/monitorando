package br.com.imd.pdse.monitorando.domain;

import br.com.imd.pdse.monitorando.domain.generic.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "TOPIC")
public class Topic extends AbstractEntity {

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "MESSAGE", nullable = false)
    private String message;

    @Column(name = "OPEN", nullable = false)
    private boolean open;

    @ManyToOne
    private Report report;

    public Topic(String title) {
        super(Instant.now());
        this.title = title;
    }

    public Topic() {
        super(Instant.now());
    }
}
