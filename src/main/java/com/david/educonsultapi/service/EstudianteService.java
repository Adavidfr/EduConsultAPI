package com.david.educonsultapi.service;

import com.david.educonsultapi.entity.Estudiante;

import java.util.List;
import java.util.Optional;

public interface EstudianteService {

    Optional<Estudiante> findById(Long id);
    List<Estudiante> findAll();
    List<Estudiante> buscarPorNombre(String nombre);
    Estudiante crear(Estudiante estudiante);
    Optional<Estudiante> editar(Long id, Estudiante estudianteActualizado);
    void eliminar(Long id);

}
