package br.com.imd.pdse.monitorando.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UUID",unique = true, nullable = false)
    private UUID uuid;
    @Column(name = "CREATED_DATE", nullable = false, updatable = false)
    @CreatedDate
    private Instant createdDate;

    protected AbstractEntity(final Instant createdDate) {
        this.createdDate = createdDate;
    }

    protected AbstractEntity() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }
}
