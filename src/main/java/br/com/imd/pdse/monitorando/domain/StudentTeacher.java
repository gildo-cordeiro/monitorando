package br.com.imd.pdse.monitorando.domain;

import br.com.imd.pdse.monitorando.domain.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "STUDENT_TEACHER")
public class StudentTeacher extends AbstractEntity {

    @ManyToOne
    @MapsId("uuid")
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
    @MapsId("uuid")
    @JoinColumn(name = "teacher_id")
    Teacher teacher;
}
