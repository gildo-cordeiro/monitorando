package br.com.imd.pdse.monitorando.domain;

import br.com.imd.pdse.monitorando.domain.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "STUDENT")
public class Student extends AbstractEntity {

    @Serial
    private static final long serialVersionUID = 1L;
    @ManyToMany
    @JoinTable(name = "student_classroom", joinColumns = @JoinColumn(name = "classroom_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Classroom> classrooms;

}
