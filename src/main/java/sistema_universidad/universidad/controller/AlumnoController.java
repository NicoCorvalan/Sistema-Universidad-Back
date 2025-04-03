package sistema_universidad.universidad.controller;

import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sistema_universidad.universidad.dto.RequestAlumnoDTO;
import sistema_universidad.universidad.dto.ResponseAlumnoDTO;
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
    public List<ResponseAlumnoDTO> mostrarAlumnos() {
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
    public ResponseEntity<?> buscarPorId(@Parameter(description = "ID del alumno a buscar") @PathVariable Long id) {
        ResponseAlumnoDTO responseAlumnoDTO = alumnoService.buscarAlumnoPorId(id);

        if (responseAlumnoDTO != null) {
            return ResponseEntity.ok(responseAlumnoDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El alumno con ID " + id + " no existe en la base de datos.");
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
    public ResponseEntity<ResponseAlumnoDTO> crearAlumno(@Valid @RequestBody RequestAlumnoDTO requestAlumnoDTO) {
        ResponseAlumnoDTO responseAlumnoDTO = alumnoService.crearAlumno(requestAlumnoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseAlumnoDTO);
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
    public ResponseEntity<ResponseAlumnoDTO> editarAlumno(
            @PathVariable Long id,
            @Valid @RequestBody RequestAlumnoDTO requestAlumnoDTO) {

        ResponseAlumnoDTO alumnoActualizado = alumnoService.editarAlumno(id, requestAlumnoDTO);
        return new ResponseEntity<>(alumnoActualizado, HttpStatus.OK);
    }

}
