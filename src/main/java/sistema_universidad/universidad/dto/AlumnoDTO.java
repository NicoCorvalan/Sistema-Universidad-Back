package sistema_universidad.universidad.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sistema_universidad.universidad.enums.Estado;

@Getter
@Setter
@AllArgsConstructor
public class AlumnoDTO {

    //Esto es lo que se muestra al cliente
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String carreraNombre; // Este campo mostrará el nombre de la carrera
    private String telefono;
    private String numeroLegajo;
    private Estado estado;
}

