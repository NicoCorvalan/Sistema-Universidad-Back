package sistema_universidad.universidad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sistema_universidad.universidad.model.Materia;
import sistema_universidad.universidad.service.MateriaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;


@RestController
@RequestMapping("/universidad/materias")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @Operation(summary = "Mostrar todas las Materias")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found all subjects",
        content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = Materia.class)) })
    })
    @GetMapping
    public List<Materia> getMaterias() {
        return materiaService.mostrarMateria();
    }

    @Operation(summary = "Mostrar la Materia segun el ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the subject",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = Materia.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Subject not found",
            content = @Content)
    })
    @GetMapping("/{id}")
    public Materia buscarPorId(@Parameter(description = "id of subject to be searched") @PathVariable Long id) {
        return materiaService.buscarPorId(id);
    }

    @Operation(summary = "Crear una nueva Materia")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Subject created successfully",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = Materia.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid input",content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> crearMateria(@RequestBody Materia materia) {
        materiaService.crearMateria(materia);
        return new ResponseEntity<>("Materia creada con éxito", HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar una Materia")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Subject updated successfully",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = Materia.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid input",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Subject not found",
            content = @Content)
    })
    @PutMapping
    public ResponseEntity<?> editarMateria(@RequestBody Materia materia) {
        materiaService.editarMateria(materia);
        return new ResponseEntity<>("Materia editada con éxito", HttpStatus.OK);
    }

    @Operation(summary = "Eliminar Materia segun el ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Subject deleted successfully",
            content = @Content),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Subject not found",
            content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMateria(@Parameter(description = "id of subject to be deleted") @PathVariable Long id) {
        materiaService.eliminarMateria(id);
        return new ResponseEntity<>("Materia eliminada con éxito", HttpStatus.OK);
    }
}
