package sistema_universidad.universidad.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class CarreraDTO {
    private Integer id;
    private String nombre;
    private int duracion;
}
