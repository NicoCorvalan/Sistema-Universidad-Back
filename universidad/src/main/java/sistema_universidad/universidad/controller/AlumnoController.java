package sistema_universidad.universidad.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sistema_universidad.universidad.dto.AlumnoDTO;
import sistema_universidad.universidad.dto.CrearAlumnoDTO;
import sistema_universidad.universidad.dto.MateriaDTO;
import sistema_universidad.universidad.model.Alumno;
import sistema_universidad.universidad.service.AlumnoServiceImpl;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequiredArgsConstructor
@RequestMapping("/universidad/alumnos")
public class AlumnoController {

    private final AlumnoServiceImpl alumnoService;

    @Operation(summary ="Mostrar todos los Alumnos")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found all students",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Alumno.class)) })
    })
    @GetMapping
    public List<AlumnoDTO> mostrarAlumnos() {
        return alumnoService.mostrarAlumnos();
    }

    @Operation(summary = "Mostrar el Alumno segun el ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the student",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Alumno.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AlumnoDTO> buscarPorId(@Parameter(description = "id of student to be searched")@PathVariable Long id) {
        AlumnoDTO alumnoDTO = alumnoService.buscarAlumnoPorId(id);
        if (alumnoDTO != null) {
            return ResponseEntity.ok(alumnoDTO);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Crear un nuevo Alumno")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Student created successfully",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Alumno.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping()
    public ResponseEntity<AlumnoDTO> crearAlumno(@RequestBody CrearAlumnoDTO crearAlumnoDTO) {
        AlumnoDTO alumnoDTO = alumnoService.crearAlumno(crearAlumnoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDTO);
    }


    @Operation(summary = "Actualizar un alumno")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Student updated successfully",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Alumno.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> editarAlumno(
            @PathVariable Long id,
            @RequestBody CrearAlumnoDTO crearAlumnoDTO) {

        AlumnoDTO alumnoActualizado = alumnoService.editarAlumno(id, crearAlumnoDTO);
        return new ResponseEntity<>(alumnoActualizado, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar un Alumno segun el ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Student deleted successfully",
            content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Student not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAlumno(@Parameter(description = "id of student to be deleted")@PathVariable Long id) {
        alumnoService.eliminarAlumno(id);
        return new ResponseEntity<>("Alumno eliminado satisfactoriamente", HttpStatus.OK);
    }
}
