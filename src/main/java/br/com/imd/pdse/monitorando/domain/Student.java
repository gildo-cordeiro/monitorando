package br.com.imd.pdse.monitorando.domain;

import br.com.imd.pdse.monitorando.domain.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "STUDENT")
public class Student extends AbstractEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_UUID", referencedColumnName = "UUID")
    private User user;

    @ManyToMany
    @JoinTable(name = "student_classroom", joinColumns = @JoinColumn(name = "classroom_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Classroom> classrooms;

    @OneToMany(mappedBy = "student")
    private Set<StudentTeacher> students;

    public Student(User user) {
        super(Instant.now());
        classrooms = Collections.emptyList();
        this.user = user;
    }

    public Student() {
        super(Instant.now());
    }
}
