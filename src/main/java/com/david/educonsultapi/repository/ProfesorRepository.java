package com.david.educonsultapi.repository;

import com.david.educonsultapi.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    List<Profesor> findByNombreContainingIgnoreCase(String nombre);

}
