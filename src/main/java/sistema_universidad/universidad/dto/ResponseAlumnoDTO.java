package sistema_universidad.universidad.dto;

import lombok.*;
import sistema_universidad.universidad.enums.Estado;

@Data
public class ResponseAlumnoDTO {

    //Esto es lo que se muestra al cliente
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String carrera; // Este campo mostrará el nombre de la carrera
    private String telefono;
    private String numeroLegajo;
    private Estado estado;
}

