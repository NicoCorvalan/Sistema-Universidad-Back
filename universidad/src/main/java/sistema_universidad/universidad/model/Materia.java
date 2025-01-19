package sistema_universidad.universidad.model;


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

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "El ID de la carrera es obligatorio")
    @JoinColumn(name="carrera_id")
    private Carrera carrera;

    @Column(name = "horas_cursado")
    @Min(value = 1, message = "Las horas de cursado deben ser mayores a 0")
    private int horasCursado;

    @Enumerated(EnumType.STRING)
    @Column(name = "duracion")
    @NotNull(message = "La duración es obligatoria")
    private Duracion duracion;
}