package com.david.educonsultapi.service.Impl;

import com.david.educonsultapi.entity.Curso;
import com.david.educonsultapi.entity.Estudiante;
import com.david.educonsultapi.exception.EstudianteNotFoundException;
import com.david.educonsultapi.repository.CursoRepository;
import com.david.educonsultapi.repository.EstudianteRepository;
import com.david.educonsultapi.service.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;

    @Override
    public Optional<Estudiante> findById(Long id) {
        return Optional.ofNullable(estudianteRepository.findById(id)
                .orElseThrow(() -> new EstudianteNotFoundException("Estudiante con id " + id + " no encontrado")));
    }

    @Override
    public List<Estudiante> findAll() {
        return estudianteRepository.findAll();
    }

    @Override
    public List<Estudiante> buscarPorNombre(String nombre) {
        List<Estudiante> estudiantes = estudianteRepository.findByNombreContainingIgnoreCase(nombre);

        if (estudiantes.isEmpty()) {
            throw new EstudianteNotFoundException("No se encontraron estudiantes con el nombre: " + nombre);
        }

        return estudiantes;
    }


    @Override
    public Estudiante crear(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public Optional<Estudiante> editar(Long id, Estudiante estudianteActualizado) {
        return estudianteRepository.findById(id)
                .map(estudiante -> {
                    estudiante.setNombre(estudianteActualizado.getNombre());
                    estudiante.setApellido(estudianteActualizado.getApellido());
                    estudiante.setTelefono(estudianteActualizado.getTelefono());
                    estudiante.setCorreoElectronico(estudianteActualizado.getCorreoElectronico());
                    estudiante.setCursos(estudianteActualizado.getCursos());
                    return estudianteRepository.save(estudiante);
                });
    }


    @Override
    public void eliminar(Long id) {
        Optional<Estudiante> estudiante = estudianteRepository.findById(id);
        if (estudiante.isPresent()) {
            Estudiante e = estudiante.get();

            for (Curso curso : e.getCursos()) {
                curso.getEstudiantes().remove(e);
                cursoRepository.save(curso);
            }

            estudianteRepository.delete(e);
        } else {
            throw new EstudianteNotFoundException("Estudiante con id " + id + " no encontrado");
        }
    }

}
