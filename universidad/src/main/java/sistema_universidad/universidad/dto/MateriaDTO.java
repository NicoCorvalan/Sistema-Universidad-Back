package sistema_universidad.universidad.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MateriaDTO {

    private Long id;
    private String nombre;
    private String carreraNombre;
    private Enum duracion;
    private int horasCursado;
}
