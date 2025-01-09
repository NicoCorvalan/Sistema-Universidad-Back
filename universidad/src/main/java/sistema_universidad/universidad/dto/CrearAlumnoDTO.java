package sistema_universidad.universidad.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearAlumnoDTO {
    private String nombre;
    private String apellido;
    private String dni;
    private Integer carreraId; // Campo para recibir el ID de la carrera
    private String telefono;
    private String numeroLegajo;
    private String estado;
}

