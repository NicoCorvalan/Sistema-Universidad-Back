package sistema_universidad.universidad.controller;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sistema_universidad.universidad.dto.CrearMateriaDTO;
import sistema_universidad.universidad.dto.MateriaDTO;
import sistema_universidad.universidad.model.Materia;
import sistema_universidad.universidad.service.MateriaServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@AllArgsConstructor
@RestController
@RequestMapping("/universidad/materias")
public class MateriaController {

    private final MateriaServiceImpl materiaService;

    @Operation(summary = "Mostrar todas las Materias")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found all subjects",
        content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = Materia.class)) })
    })
    @GetMapping
    public List<MateriaDTO> mostrarMaterias() {
        return materiaService.mostrarMaterias();
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
    public ResponseEntity<MateriaDTO> buscarPorId(@Parameter(description = "id of subject to be searched") @PathVariable Long id) {
        MateriaDTO materiaDTO = materiaService.buscarMateriaPorId(id);
        if (materiaDTO != null) {
            return ResponseEntity.ok(materiaDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Crear una nueva Materia")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Subject created successfully",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = Materia.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid input",content = @Content)
    })
    @PostMapping
    public ResponseEntity<MateriaDTO> crearMateria(@RequestBody CrearMateriaDTO crearMateriaDTO) {
        MateriaDTO materiaDTO = materiaService.crearMateria(crearMateriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(materiaDTO);
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
    @PutMapping("/{id}")
    public ResponseEntity<?> editarMateria(@PathVariable Long id, @RequestBody CrearMateriaDTO crearMateriaDTO) {
        MateriaDTO materiaActualizada = materiaService.editarMateria(id, crearMateriaDTO);
        return new ResponseEntity<>(materiaActualizada, HttpStatus.OK);
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
