package com.david.educonsultapi.service;

import com.david.educonsultapi.entity.Profesor;

import java.util.List;
import java.util.Optional;

public interface ProfesorService {

    Optional<Profesor> findById(Long id);
    List<Profesor> findAll();
    List<Profesor> buscarPorNombre(String nombre);
    Profesor crear(Profesor profesor);
    Optional<Profesor> editar(Long id, Profesor profesorActualizado);
    void eliminar(Long id);

}
