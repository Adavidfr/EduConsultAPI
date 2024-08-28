package com.david.educonsultapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String telefono;
    private String correoElectronico;

    @ManyToMany(mappedBy = "estudiantes")
    @JsonIgnore
    private List<Curso> cursos;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<Curso> getCursos() {
        return cursos;
    }
}
