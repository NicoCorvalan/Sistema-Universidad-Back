package sistema_universidad.universidad.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import sistema_universidad.universidad.enums.Duracion;

import java.util.List;

@Data
@Builder
public class RequestMateriaDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "Debe indicar al menos una carrera")
    private List<Integer> carreraIds; // Si querés asegurar al menos una carrera, usa @Size

    @NotNull(message = "Las horas de cursado son obligatorias")
    @Min(value = 1, message = "Las horas de cursado deben ser mayores a 0")
    private int horasCursado;

    @NotNull(message = "La duración es obligatoria")
    private Duracion duracion;

}
