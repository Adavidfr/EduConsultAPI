package com.david.educonsultapi.controller;

import com.david.educonsultapi.entity.Profesor;
import com.david.educonsultapi.exception.ProfesorNotFoundException;
import com.david.educonsultapi.service.ProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
@RequiredArgsConstructor
public class ProfesorController {

    private final ProfesorService profesorService;

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> getProfesorById(@PathVariable Long id) {
        Profesor profesor = profesorService.findById(id)
                .orElseThrow(() -> new ProfesorNotFoundException("Profesor con id " + id + " no encontrado"));
        return new ResponseEntity<>(profesor, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Profesor>> findAll() {
        List<Profesor> profesores = profesorService.findAll();
        return new ResponseEntity<>(profesores, HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Profesor>> buscarProfesorPorNombre(@RequestParam String nombre) {
        List<Profesor> profesores = profesorService.buscarPorNombre(nombre);
        if (profesores.isEmpty()) {
            throw new ProfesorNotFoundException("No se encontraron profesores con el nombre: " + nombre);
        }
        return new ResponseEntity<>(profesores, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Profesor> crearProfesor(@RequestBody Profesor profesor) {
        Profesor nuevoProfesor = profesorService.crear(profesor);
        return new ResponseEntity<>(nuevoProfesor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesor> editarProfesor(@PathVariable Long id, @RequestBody Profesor profesorDetalles) {
        Profesor profesorActualizado = profesorService.editar(id, profesorDetalles)
                .orElseThrow(() -> new ProfesorNotFoundException("Profesor con id " + id + " no encontrado"));
        return new ResponseEntity<>(profesorActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProfesor(@PathVariable Long id) {
        try {
            profesorService.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ProfesorNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
