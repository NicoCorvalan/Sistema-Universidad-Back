package sistema_universidad.universidad.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import sistema_universidad.universidad.enums.Estado;

@Data
public class RequestAlumnoDTO {

    //Esto es lo que recibe el servidor
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 20, message = "El nombre debe tener entre 3 y 20 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;

    @NotBlank(message = "El DNI no puede estar vacío")
    @Pattern(regexp = "^[0-9]{8}$", message = "El DNI debe ser un número de 8 dígitos")
    private String dni;

    @NotNull(message = "El alumno debe estar asociado a una carrera")
    private Integer carreraId; // Este sigue igual, es el ID de la carrera

    @Pattern(regexp = "^[0-9]{10}$", message = "El teléfono debe tener 10 dígitos")
    private String telefono;

    @NotBlank(message = "El Legajo no puede estar vacío")
    @Size(min = 4, max = 10, message = "El Legajo debe tener entre 4 y 10 caracteres")
    private String numeroLegajo;

    private Estado estado = Estado.ACTIVO;
}

