package sistema_universidad.universidad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema_universidad.universidad.dto.CarreraDTO;
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
    public List<CarreraDTO> mostrarCarrera() {
        return carreraService.mostrarCarrera();
    }

    @Operation(summary = "Mostrar Carrera segun el ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the career",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Carrera.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Career not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public CarreraDTO buscarPorId(@Parameter(description = "id of career to be searched") @PathVariable Long id) {
        Carrera carrera = carreraService.buscarPorId(id);
        if (carrera != null) {
            return new CarreraDTO(carrera.getId(), carrera.getNombre(), carrera.getDuracion());
        } else {
            return null; // O manejarlo de alguna manera
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
    public ResponseEntity<?> crearCarrera(@RequestBody Carrera carrera) {
        carreraService.crearCarrera(carrera);
        return new ResponseEntity<>("Carrera creada exitosamente", HttpStatus.CREATED);
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
    @PutMapping
    public ResponseEntity<?> editarCarrera(@RequestBody Carrera carrera) {
        carreraService.editarCarrera(carrera);
        return new ResponseEntity<>("Carrera modificada exitosamente", HttpStatus.OK);
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
    public ResponseEntity<?> eliminarCarrera(@Parameter(description = "id of career to be deleted") @PathVariable Long id) {
        carreraService.eliminarCarrera(id);
        return new ResponseEntity<>("Carrera eliminada exitosamente", HttpStatus.OK);
    }
}