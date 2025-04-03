package sistema_universidad.universidad.dto;

import lombok.Builder;
import lombok.Data;
import sistema_universidad.universidad.enums.Duracion;
import java.util.List;

@Builder
@Data
public class ResponseMateriaDTO {

    private Long id;
    private String nombre;
    private List<String> carrera;
    private Duracion duracion;
    private int horasCursado;
}
