package sistema_universidad.universidad.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sistema_universidad.universidad.enums.Duracion;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MateriaDTO {

    private Long id;
    private String nombre;
    private List<String> carrera;
    private Duracion duracion;
    private int horasCursado;
}
