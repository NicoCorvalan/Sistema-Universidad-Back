package sistema_universidad.universidad.dto;

import lombok.*;
import sistema_universidad.universidad.enums.Duracion;

import java.util.List;

@Data
@Builder
public class CrearMateriaDTO {

    private String nombre;
    private List<Integer> carreraIds;
    private int horasCursado;
    private Duracion duracion;

}
