package br.com.imd.pdse.monitorando.domain.generic;

import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

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

    protected AbstractEntity(final Instant createdDate, final UUID uuid) {
        this.createdDate = createdDate;
        this.uuid = uuid;
    }

    protected AbstractEntity() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid){
        this.uuid = uuid;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }
}
