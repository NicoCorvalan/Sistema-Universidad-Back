package sistema_universidad.universidad.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sistema_universidad.universidad.enums.Duracion;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CrearMateriaDTO {

    private String nombre;
    private List<Integer> carreraIds;
    private int horasCursado;
    private Duracion duracion;

}
