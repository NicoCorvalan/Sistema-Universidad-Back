package sistema_universidad.universidad.dto;

import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
@Builder
public class CrearCarreraDTO {
    private String nombre;
    private int duracion;
    private List<Long> materiasIds;  // O la lista de materias, dependiendo de tu diseño
}


