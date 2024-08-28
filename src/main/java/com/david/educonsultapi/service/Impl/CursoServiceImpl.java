package com.david.educonsultapi.service.Impl;

import com.david.educonsultapi.entity.Curso;
import com.david.educonsultapi.entity.Estudiante;
import com.david.educonsultapi.entity.Profesor;
import com.david.educonsultapi.exception.CursoNotFoundException;
import com.david.educonsultapi.exception.EstudianteNotFoundException;
import com.david.educonsultapi.exception.ProfesorNotFoundException;
import com.david.educonsultapi.repository.CursoRepository;
import com.david.educonsultapi.repository.EstudianteRepository;
import com.david.educonsultapi.repository.ProfesorRepository;
import com.david.educonsultapi.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;
    private final EstudianteRepository estudianteRepository;
    private final ProfesorRepository profesorRepository;

    @Override
    public Optional<Curso> findById(Long id) {
        return Optional.ofNullable(cursoRepository.findById(id)
                .orElseThrow(() -> new CursoNotFoundException("Curso con id " + id + " no encontrado")));
    }

    @Override
    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    @Override
    public List<Curso> buscarPorNombre(String nombre) {
        List<Curso> cursos = cursoRepository.findByNombreContainingIgnoreCase(nombre);

        if (cursos.isEmpty()) {
            throw new CursoNotFoundException("No se encontraron cursos con el nombre: " + nombre);
        }

        return cursos;
    }

    @Override
    public Curso crear(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public Optional<Curso> editar(Long id, Curso cursoActualizado) {
        return cursoRepository.findById(id)
                .map(curso -> {
                    curso.setNombre(cursoActualizado.getNombre());
                    curso.setDescripcion(cursoActualizado.getDescripcion());
                    curso.setProfesor(cursoActualizado.getProfesor());
                    curso.setEstudiantes(cursoActualizado.getEstudiantes());
                    return cursoRepository.save(curso);
                });
    }

    @Override
    public void eliminar(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new CursoNotFoundException("Curso con id " + id + " no encontrado");
        }
        cursoRepository.deleteById(id);
    }

    @Override
    public Curso asignarCursoAEstudiante(Long cursoId, Long estudianteId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new CursoNotFoundException("Curso con id " + cursoId + " no encontrado"));
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new EstudianteNotFoundException("Estudiante con id " + estudianteId + " no encontrado"));

        curso.getEstudiantes().add(estudiante);
        estudiante.getCursos().add(curso);

        cursoRepository.save(curso);
        estudianteRepository.save(estudiante);

        return curso;
    }

    @Override
    public Curso asignarCursoAProfesor(Long cursoId, Long profesorId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new CursoNotFoundException("Curso con id " + cursoId + " no encontrado"));
        Profesor profesor = profesorRepository.findById(profesorId)
                .orElseThrow(() -> new ProfesorNotFoundException("Profesor con id " + profesorId + " no encontrado"));

        curso.setProfesor(profesor);

        cursoRepository.save(curso);

        return curso;
    }


}
