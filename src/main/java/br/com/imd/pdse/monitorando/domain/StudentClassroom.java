package br.com.imd.pdse.monitorando.domain;

import br.com.imd.pdse.monitorando.domain.generic.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "STUDENT_CLASSROOM")
public class StudentClassroom extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    Classroom classroom;

    public StudentClassroom(){
        super(Instant.now());
    }
}
