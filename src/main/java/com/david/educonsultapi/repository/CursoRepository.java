package com.david.educonsultapi.repository;

import com.david.educonsultapi.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    List<Curso> findByNombreContainingIgnoreCase(String nombre);

}
