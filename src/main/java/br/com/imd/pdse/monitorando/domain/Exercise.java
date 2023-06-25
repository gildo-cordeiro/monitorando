package br.com.imd.pdse.monitorando.domain;

import br.com.imd.pdse.monitorando.domain.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "EXERCISE")
public class Exercise extends AbstractEntity {

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @ManyToOne
    private Classroom classroom;

    public Exercise(String title, String description, Classroom classroom) {
        super(Instant.now());
        this.title = title;
        this.description = description;
        this.classroom = classroom;
    }

    public Exercise(String title, String description, UUID uuid) {
        super(Instant.now(), uuid);
        this.title = title;
        this.description = description;
    }

    public Exercise(){
        super(Instant.now());
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", classroom=" + classroom +
                '}';
    }
}
