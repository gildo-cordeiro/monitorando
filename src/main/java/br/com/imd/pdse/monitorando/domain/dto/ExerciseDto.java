package br.com.imd.pdse.monitorando.domain.dto;

import br.com.imd.pdse.monitorando.domain.Classroom;
import br.com.imd.pdse.monitorando.domain.Exercise;
import br.com.imd.pdse.monitorando.domain.Monitor;
import br.com.imd.pdse.monitorando.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDto {

    private String title;

    private String description;

    private UUID classroomId;

    private User user;

}
