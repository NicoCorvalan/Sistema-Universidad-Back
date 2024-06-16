# Sistema de Gestión Universitaria

Este proyecto es un sistema de gestión universitaria desarrollado utilizando Spring Boot

## Funcionalidades

- **Gestión de Alumnos:** Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los datos de los alumnos, incluyendo nombre, apellido, DNI, carrera, teléfono y número de legajo.
  
- **Gestión de Carreras:** Permite administrar las carreras universitarias ofrecidas por la institución, incluyendo nombre de la carrera y lista de alumnos inscritos en cada una.

- **Gestión de Materias:** Permite gestionar las materias disponibles en cada carrera, incluyendo nombre de la materia, duración (anual o cuatrimestral) y cantidad de horas de cursado.

## Tecnologías Utilizadas

- **Spring Boot:** Framework de desarrollo de aplicaciones empresariales en Java.
  
- **Spring Data JPA:** Herramienta que simplifica el acceso y la manipulación de datos en las bases de datos relacionales a través de la implementación de repositorios basados en interfaces.

- **Hibernate:** Framework de mapeo objeto-relacional para la gestión de la capa de persistencia.

---

## Cómo Utilizar

Este proyecto proporciona un sistema de gestión universitaria que puedes utilizar para administrar alumnos, carreras y materias. A continuación, se detallan los pasos para poner en funcionamiento el sistema en tu entorno local:

### Requisitos Previos

- Java 8 o superior instalado en tu sistema.
  
- Maven 3.x para construir y administrar el proyecto.
  
- MySQL

### Instrucciones

1. Clona este repositorio a tu máquina local utilizando el siguiente comando:
   ```
   git clone https://github.com/TU_USUARIO/sistema-universidad.git
   ```
   Reemplaza `TU_USUARIO` con tu nombre de usuario de GitHub.

2. Importa el proyecto en tu IDE favorito como un proyecto Maven existente.

3. Configura la conexión a tu base de datos MySQL en el archivo `application.properties` ubicado en `src/main/resources`. Asegúrate de actualizar las propiedades `spring.datasource.url`, `spring.datasource.username` y `spring.datasource.password` con los valores correspondientes.

4. Ejecuta el script de creación de la base de datos que se encuentra en `src/main/resources/db/schema.sql` para crear la estructura de la base de datos y las tablas necesarias.

5. Una vez configurada la base de datos, inicia la aplicación ejecutando la clase principal `UniversidadApplication.java`.

6. Accede a la aplicación a través de tu navegador web utilizando la URL `http://localhost:8080`.

# Documentación de la API

La documentación de la API generada por Swagger está disponible en la siguiente URL:

[Documentación de la API](http://localhost:8080/swagger-ui/index.html)

Desde esta interfaz, puedes explorar y probar todas las operaciones disponibles en la API.

8. Desde la interfaz de usuario, podrás realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los datos de alumnos, carreras y materias.

¡Y eso es todo! Ahora puedes comenzar a utilizar el sistema de gestión universitaria para administrar los datos de tu institución educativa.

---
