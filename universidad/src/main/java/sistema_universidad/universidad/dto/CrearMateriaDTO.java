package sistema_universidad.universidad.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sistema_universidad.universidad.enums.Duracion;

@Getter
@Setter
@AllArgsConstructor
public class CrearMateriaDTO {

    private String nombre;
    private Integer carreraId;
    private int horasCursado;
    private Duracion duracion;

}
