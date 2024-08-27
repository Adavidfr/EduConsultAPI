package com.david.educonsultapi.controller;

import com.david.educonsultapi.entity.Estudiante;
import com.david.educonsultapi.exception.EstudianteNotFoundException;
import com.david.educonsultapi.service.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> getEstudianteById(@PathVariable Long id) {
        Estudiante estudiante = estudianteService.findById(id)
                .orElseThrow(() -> new EstudianteNotFoundException("Estudiante con id " + id + " no encontrado"));
        return new ResponseEntity<>(estudiante, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Estudiante>> findAll() {
        List<Estudiante> estudiantes = estudianteService.findAll();
        return new ResponseEntity<>(estudiantes, HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Estudiante>> buscarEstudiantePorNombre(@RequestParam String nombre) {
        List<Estudiante> estudiantes = estudianteService.buscarPorNombre(nombre);
        if (estudiantes.isEmpty()) {
            throw new EstudianteNotFoundException("No se encontraron estudiantes con el nombre: " + nombre);
        }
        return new ResponseEntity<>(estudiantes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Estudiante> crearEstudiante(@RequestBody Estudiante estudiante) {
        Estudiante nuevoEstudiante = estudianteService.crear(estudiante);
        return new ResponseEntity<>(nuevoEstudiante, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> editarEstudiante(@PathVariable Long id, @RequestBody Estudiante estudianteDetalles) {
        Estudiante estudianteActualizado = estudianteService.editar(id, estudianteDetalles)
                .orElseThrow(() -> new EstudianteNotFoundException("Estudiante con id " + id + " no encontrado"));
        return new ResponseEntity<>(estudianteActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
