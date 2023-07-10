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
@Setter
@Getter
@Table(name = "CALENDAR")
public class Calendar extends AbstractEntity {

    @Column(name = "ENDTIME", nullable = false)
    private String endtime;

    @Column(name = "FORM", nullable = false)
    private String form;

    @Column(name = "MESSAGE", nullable = false)
    private String message;

    public Calendar(){
        super(Instant.now());
    }

}
