package sistema_universidad.universidad.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sistema_universidad.universidad.enums.Estado;

@Getter
@Setter
@AllArgsConstructor
public class CrearAlumnoDTO {

    //Esto es lo que recibe el servidor
    private String nombre;
    private String apellido;
    private String dni;
    private Integer carreraId; // Campo para recibir el ID de la carrera
    private String telefono;
    private String numeroLegajo;
    private Estado estado = Estado.ACTIVO;
}

