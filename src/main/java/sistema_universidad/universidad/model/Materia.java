package sistema_universidad.universidad.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import sistema_universidad.universidad.enums.Duracion;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "materias")
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    @ManyToMany(mappedBy = "materias")
    @JsonIgnore
    private List<Carrera> carreras = new ArrayList<>();

    @Column(name = "horas_cursado")
    @Min(value = 1, message = "Las horas de cursado deben ser mayores a 0")
    private int horasCursado;

    @Enumerated(EnumType.STRING)
    @Column(name = "duracion")
    @NotNull(message = "La duración es obligatoria")
    private Duracion duracion;
}