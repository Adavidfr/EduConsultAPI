package com.david.educonsultapi.repository;

import com.david.educonsultapi.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    List<Estudiante> findByNombreContainingIgnoreCase(String nombre);

}
