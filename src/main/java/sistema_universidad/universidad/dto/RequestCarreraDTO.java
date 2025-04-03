package sistema_universidad.universidad.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
@Builder
public class RequestCarreraDTO {
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 20, message = "El nombre debe tener entre 3 y 20 caracteres")
    private String nombre;

    @Min(value = 3, message = "La carrera debe durar como mínimo 3 años")
    private int duracion;

    @NotEmpty(message = "Debe indicar al menos una materia")
    private List<Long> materiasIds;  // O lista vacía según el caso, pero al menos validar que llegue algo
}


