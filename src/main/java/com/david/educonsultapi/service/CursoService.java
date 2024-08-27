package com.david.educonsultapi.service;

import com.david.educonsultapi.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    Optional<Curso> findById(Long id);
    List<Curso> findAll();
    List<Curso> buscarPorNombre(String nombre);
    Curso crear(Curso curso);
    Optional<Curso> editar(Long id, Curso cursoActualizado);
    void eliminar(Long id);

}
