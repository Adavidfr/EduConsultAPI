package com.david.educonsultapi.service.Impl;

import com.david.educonsultapi.entity.Curso;
import com.david.educonsultapi.entity.Profesor;
import com.david.educonsultapi.exception.ProfesorNotFoundException;
import com.david.educonsultapi.repository.CursoRepository;
import com.david.educonsultapi.repository.ProfesorRepository;
import com.david.educonsultapi.service.ProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository profesorRepository;
    private final CursoRepository cursoRepository;

    @Override
    public Optional<Profesor> findById(Long id) {
        return Optional.ofNullable(profesorRepository.findById(id)
                .orElseThrow(() -> new ProfesorNotFoundException("Profesor con id " + id + " no encontrado")));
    }

    @Override
    public List<Profesor> findAll() {
        return profesorRepository.findAll();
    }

    @Override
    public List<Profesor> buscarPorNombre(String nombre) {
        List<Profesor> profesores = profesorRepository.findByNombreContainingIgnoreCase(nombre);

        if (profesores.isEmpty()) {
            throw new ProfesorNotFoundException("No se encontraron profesores con el nombre: " + nombre);
        }

        return profesores;
    }

    @Override
    public Profesor crear(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    @Override
    public Optional<Profesor> editar(Long id, Profesor profesorActualizado) {
        return profesorRepository.findById(id)
                .map(profesor -> {
                    profesor.setNombre(profesorActualizado.getNombre());
                    profesor.setApellido(profesorActualizado.getApellido());
                    profesor.setTelefono(profesorActualizado.getTelefono());
                    profesor.setCorreoElectronico(profesorActualizado.getCorreoElectronico());
                    profesor.setCursos(profesorActualizado.getCursos());
                    return profesorRepository.save(profesor);
                });
    }

    @Override
    public void eliminar(Long id) {
        Optional<Profesor> profesorOptional = profesorRepository.findById(id);
        if (profesorOptional.isPresent()) {
            Profesor profesor = profesorOptional.get();

            // Desvincular el profesor de los cursos
            for (Curso curso : profesor.getCursos()) {
                curso.setProfesor(null); // Desvincular al profesor del curso
                cursoRepository.save(curso); // Guardar el curso sin el profesor
            }

            profesorRepository.delete(profesor); // Eliminar el profesor
        } else {
            throw new ProfesorNotFoundException("Profesor con id " + id + " no encontrado");
        }
    }


}
