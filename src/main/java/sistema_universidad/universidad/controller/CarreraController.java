package sistema_universidad.universidad.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sistema_universidad.universidad.dto.RequestCarreraDTO;
import sistema_universidad.universidad.dto.ResponseCarreraDTO;
import sistema_universidad.universidad.model.Carrera;
import sistema_universidad.universidad.service.CarreraService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/universidad/carreras")
public class CarreraController {

    private final CarreraService carreraService;

    @Operation(summary = "Mostrar todas las Carreras")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found all careers",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Carrera.class))})
    })
    @GetMapping
    public List<ResponseCarreraDTO> mostrarCarrera() {
        return carreraService.mostrarCarrera();
    }

    @Operation(summary = "Mostrar Carrera segun el ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the career",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseCarreraDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Career not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseCarreraDTO buscarPorId(@Parameter(description = "id of career to be searched") @PathVariable Integer id) {
        try {
            Carrera carrera = carreraService.buscarPorId(id);
            return new ResponseCarreraDTO(carrera.getId(), carrera.getNombre(), carrera.getDuracion());
        } catch (RuntimeException e) { // Captura la excepción lanzada en el servicio
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrera no encontrada");
        }
    }

    @Operation(summary = "Crear una nueva Carrera")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Career created successfully",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Carrera.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<ResponseCarreraDTO> crearCarrera(@Valid @RequestBody RequestCarreraDTO requestCarreraDTO) {
        // Llamar al servicio con el DTO directamente
        ResponseCarreraDTO responseCarreraDTO = carreraService.crearCarrera(requestCarreraDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseCarreraDTO);
    }


    @Operation(summary = "Actualizar una Carrera")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Career updated successfully",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Carrera.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Career not found",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseCarreraDTO> editarCarrera(@PathVariable Integer id,@Valid @RequestBody RequestCarreraDTO requestCarreraDTO) {
        // Buscar la carrera existente
        ResponseCarreraDTO responseCarreraDTO = carreraService.editarCarrera(id, requestCarreraDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseCarreraDTO);

    }
    @Operation(summary = "Eliminar una Carrera segun el ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Career deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Career not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCarrera(@Parameter(description = "id of career to be deleted") @PathVariable Integer id) {
        try {
            carreraService.eliminarCarrera(id);
            return new ResponseEntity<>("Carrera eliminada exitosamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Carrera no encontrada", HttpStatus.NOT_FOUND);
        }
    }
}