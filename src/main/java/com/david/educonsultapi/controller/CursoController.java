package com.david.educonsultapi.controller;

import com.david.educonsultapi.entity.Curso;
import com.david.educonsultapi.exception.CursoNotFoundException;
import com.david.educonsultapi.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Long id) {
        Curso curso = cursoService.findById(id)
                .orElseThrow(() -> new CursoNotFoundException("Curso con id " + id + " no encontrado"));
        return new ResponseEntity<>(curso, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Curso>> findAll() {
        List<Curso> cursos = cursoService.findAll();
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Curso>> buscarCursoPorNombre(@RequestParam String nombre) {
        List<Curso> cursos = cursoService.buscarPorNombre(nombre);
        if (cursos.isEmpty()) {
            throw new CursoNotFoundException("No se encontraron cursos con el nombre: " + nombre);
        }
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Curso> crearCurso(@RequestBody Curso curso) {
        Curso nuevoCurso = cursoService.crear(curso);
        return new ResponseEntity<>(nuevoCurso, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> editarCurso(@PathVariable Long id, @RequestBody Curso cursoDetalles) {
        Curso cursoActualizado = cursoService.editar(id, cursoDetalles)
                .orElseThrow(() -> new CursoNotFoundException("Curso con id " + id + " no encontrado"));
        return new ResponseEntity<>(cursoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        try {
            cursoService.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CursoNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
