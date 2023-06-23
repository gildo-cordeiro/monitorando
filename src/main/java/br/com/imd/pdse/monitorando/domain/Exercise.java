package br.com.imd.pdse.monitorando.domain;

import br.com.imd.pdse.monitorando.domain.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "EXERCISE")
public class Exercise extends AbstractEntity {

    @Column(name = "TITLE", nullable = false)
    private String title;

    @ManyToOne
    private Classroom classroom;

    public Exercise(String title) {
        super(Instant.now());
        this.title = title;
    }

    public Exercise(){
        super(Instant.now());
    }
}
