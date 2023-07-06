package br.com.imd.pdse.monitorando.domain;

import br.com.imd.pdse.monitorando.domain.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "COMMENT")
public class Comment extends AbstractEntity {

    @Column(name = "COMMENT", nullable = false)
    private String comment;
    @ManyToOne
    private User user;
    @ManyToOne
    private Submission submission;

    public Comment(){
        super(Instant.now());
    }

}
