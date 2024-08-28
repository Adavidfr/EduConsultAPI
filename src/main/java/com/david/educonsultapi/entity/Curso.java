package com.david.educonsultapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;

    @ManyToMany
    @JoinTable(
            name = "curso_estudiante",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "estudiante_id")
    )
    @JsonIgnore
    private List<Estudiante> estudiantes;

    @JsonProperty("profesorId")
    public Long getProfesorId() {
        return profesor != null ? profesor.getId() : null;
    }

    @JsonProperty("estudianteIds")
    public List<Long> getEstudianteIds() {
        return estudiantes != null ? estudiantes.stream().map(Estudiante::getId).collect(Collectors.toList()) : null;
    }

}
