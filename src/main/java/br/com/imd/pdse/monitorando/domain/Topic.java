package br.com.imd.pdse.monitorando.domain;

import br.com.imd.pdse.monitorando.domain.generic.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "TOPIC")
public class Topic extends AbstractEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "TITLE", nullable = false)
    private String title;
    @Column(name = "MESSAGE", nullable = false)
    private String message;
    @Column(name = "OPEN", columnDefinition = "boolean default true")
    private boolean open;
    @Column(name = "ACTIVE", columnDefinition = "boolean default true")
    private boolean active;
    @Column(name = "LIKES")
    private  int likes;
    @ManyToOne
    private User user;
    @ManyToOne
    private Report report;
    private int count;

    public Topic(String title) {
        super(Instant.now());
        this.title = title;
    }

    public Topic() {
        super(Instant.now());
    }
}
