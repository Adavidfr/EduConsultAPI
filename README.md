# EduConsultAPI

## Descripción

**EduConsultAPI** es un sistema diseñado para la consulta de información de una institución educativa a través de una
API. El sistema permite gestionar y consultar información relacionada con estudiantes, profesores y cursos. Además,
incluye funcionalidades adicionales para asignar cursos a estudiantes y profesores.

### Estructura del Proyecto

El proyecto está dividido en tres entidades principales:

- **Estudiante**: Los estudiantes pueden estar inscritos en varios cursos.
- **Profesor**: Los profesores pueden impartir varios cursos.
- **Curso**: Los cursos contienen varios estudiantes y son impartidos por un profesor.

Cada entidad incluye funcionalidades básicas para crear, editar, eliminar y buscar registros, así como funciones
adicionales en la entidad `Curso` para asignar cursos a estudiantes y profesores.

### Funcionalidades Principales

- **Estudiante**
    - Buscar estudiante por nombre.
    - Buscar todos los estudiantes.
    - Buscar estudiante por ID.
    - Crear estudiante.
    - Editar estudiante.
    - Eliminar estudiante.

- **Profesor**
    - Buscar profesor por nombre.
    - Buscar todos los profesores.
    - Buscar profesor por ID.
    - Crear profesor.
    - Editar profesor.
    - Eliminar profesor.

- **Curso**
    - Buscar curso por nombre.
    - Buscar todos los cursos.
    - Buscar curso por ID.
    - Crear curso.
    - Editar curso.
    - Eliminar curso.
    - Asignar curso a profesor.
    - Asignar curso a estudiante.

## Inicialización de Datos

El proyecto incluye un seeder para inicializar la base de datos con datos de ejemplo. Esto es útil para tener un punto
de partida al probar la API.

### Instrucciones para Ejecutar el Seeder

1. Asegúrate de que la base de datos esté configurada y accesible.
2. Al iniciar la aplicación, el seeder se ejecutará automáticamente, postulando la base de datos con datos iniciales.

## Pruebas con Postman

Las pruebas de la API están disponibles en una colección de Postman que puedes importar en tu entorno local.

### Instrucciones para Importar la Colección de Postman

1. Clona el repositorio:

2. Importa la colección en Postman:

- Abre Postman.
- Ve a **File > Import**.
- Selecciona el archivo postmanTest/PostmanTests.json ubicado en la carpeta postmanTest/ dentro del proyecto.

3. Configura las variables de entorno en Postman si es necesario:

- Asegúrate de que las variables como la URL base de la API y los tokens de autenticación estén configuradas
  correctamente en Postman.

4. Ejecuta las pruebas:

- Selecciona la colección importada.
- Haz clic en **Run** para ejecutar todas las pruebas.

## Requisitos del Challenge

Este proyecto cumple con los requisitos del challenge, que son:

- Creación de una base de datos con tres tablas principales: Estudiante, Profesor y Curso.
- Inclusión de la información de teléfono y correo electrónico para estudiantes y profesores.
- Implementación de al menos cinco métodos principales de consulta de información.
- Exposición de la API para que pueda ser consultada a través de Postman.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot**
- **MySQL**
- **Postman**


