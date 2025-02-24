package sistema_universidad.universidad.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CrearCarreraDTO {
    private String nombre;
    private int duracion;
    private List<Long> materiasIds;  // O la lista de materias, dependiendo de tu diseño
}


