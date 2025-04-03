package sistema_universidad.universidad.controller;

import java.util.List;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.server.ResponseStatusException;
import sistema_universidad.universidad.dto.RequestMateriaDTO;
import sistema_universidad.universidad.dto.ResponseMateriaDTO;
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
    public List<ResponseMateriaDTO> mostrarMaterias() {
        return materiaService.mostrarMaterias();
    }

    @Operation(summary = "Mostrar la Materia segun el ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Materia no encontrada",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseMateriaDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Subject not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseMateriaDTO> buscarPorId(
            @Parameter(description = "ID de la materia a buscar") @PathVariable Long id) {

        ResponseMateriaDTO responsemateriaDTO = materiaService.buscarMateriaPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Materia no encontrada"));

        return ResponseEntity.ok(responsemateriaDTO);
    }


    @Operation(summary = "Crear una nueva Materia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Subject created successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Materia.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",content = @Content)
    })
    @PostMapping
    public ResponseEntity<ResponseMateriaDTO> crearMateria(@Valid @RequestBody RequestMateriaDTO requestMateriaDTO) {
        ResponseMateriaDTO responsemateriaDTO = materiaService.crearMateria(requestMateriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responsemateriaDTO);
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
    public ResponseEntity<?> editarMateria(@PathVariable Long id,@Valid @RequestBody RequestMateriaDTO requestMateriaDTO) {
        ResponseMateriaDTO materiaActualizada = materiaService.editarMateria(id, requestMateriaDTO);
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
    public ResponseEntity<?> eliminarMateria(
            @Parameter(description = "ID de la materia a eliminar") @PathVariable Long id) {
        try {
            materiaService.eliminarMateria(id);
            return new ResponseEntity<>("Materia eliminada", HttpStatus.OK);
        }catch (ResponseStatusException e) {
            return new ResponseEntity<>("Carrera no encontrada", HttpStatus.NOT_FOUND);
        }
    }
}
