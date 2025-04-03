package sistema_universidad.universidad.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class ResponseCarreraDTO {
    private Integer id;
    private String nombre;
    private int duracion;
}
