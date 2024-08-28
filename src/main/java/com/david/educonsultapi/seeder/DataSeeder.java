package com.david.educonsultapi.seeder;

import com.david.educonsultapi.entity.Curso;
import com.david.educonsultapi.entity.Estudiante;
import com.david.educonsultapi.entity.Profesor;
import com.david.educonsultapi.repository.CursoRepository;
import com.david.educonsultapi.repository.EstudianteRepository;
import com.david.educonsultapi.repository.ProfesorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner dataInitializer(
            EstudianteRepository estudianteRepository,
            ProfesorRepository profesorRepository,
            CursoRepository cursoRepository
    ) {
        return args -> {
            // Crear y guardar Profesores
            Profesor profesor1 = new Profesor(null, "Ana", "Pérez", "0986333322", "ana.perez@example.com", null);
            Profesor profesor2 = new Profesor(null, "Luis", "García", "0986325322", "luis.garcia@example.com", null);
            Profesor profesor3 = new Profesor(null, "María", "López", "0986333478", "maria.lopez@example.com", null);
            Profesor profesor4 = new Profesor(null, "Carlos", "Martínez", "0986333345", "carlos.martinez@example.com", null);
            Profesor profesor5 = new Profesor(null, "Laura", "Sánchez", "0986312344", "laura.sanchez@example.com", null);

            profesorRepository.saveAll(Arrays.asList(profesor1, profesor2, profesor3, profesor4, profesor5));

            // Crear y guardar Estudiantes
            Estudiante estudiante1 = new Estudiante(null, "Pedro", "Romero", "0986334442", "pedro.romero@example.com", null);
            Estudiante estudiante2 = new Estudiante(null, "Ana", "Mendoza", "0986333389", "ana.mendoza@example.com", null);
            Estudiante estudiante3 = new Estudiante(null, "José", "Vargas", "0986333276", "jose.vargas@example.com", null);
            Estudiante estudiante4 = new Estudiante(null, "Laura", "Gutiérrez", "0986333123", "laura.gutierrez@example.com", null);
            Estudiante estudiante5 = new Estudiante(null, "Marcos", "Hernández", "0986390871", "marcos.hernandez@example.com", null);

            estudianteRepository.saveAll(Arrays.asList(estudiante1, estudiante2, estudiante3, estudiante4, estudiante5));

            // Crear y guardar Cursos
            Curso curso1 = new Curso(null, "Matemáticas I", "Curso introductorio de matemáticas", profesor1, Arrays.asList(estudiante1, estudiante2));
            Curso curso2 = new Curso(null, "Historia Mundial", "Historia desde la antigüedad hasta la era moderna", profesor2, Arrays.asList(estudiante3, estudiante4));
            Curso curso3 = new Curso(null, "Física Básica", "Conceptos fundamentales de la física", profesor3, Arrays.asList(estudiante5));
            Curso curso4 = new Curso(null, "Química Orgánica", "Estudio de compuestos orgánicos", profesor4, Arrays.asList(estudiante1, estudiante3));
            Curso curso5 = new Curso(null, "Literatura Española", "Análisis de obras literarias españolas", profesor5, Arrays.asList(estudiante2, estudiante4));

            cursoRepository.saveAll(Arrays.asList(curso1, curso2, curso3, curso4, curso5));
        };
    }
}
