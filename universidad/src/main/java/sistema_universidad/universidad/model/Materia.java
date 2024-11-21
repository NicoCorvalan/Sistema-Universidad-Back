package sistema_universidad.universidad.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    @JoinColumn(name="carrera_id")
    private Carrera carrera;

    @Enumerated(EnumType.STRING)
    @Column(name = "duracion")
    private Duracion duracion;

    @Column(name = "horas_cursado")
    private int horasCursado;

    public enum Duracion {anual, cuatrimestral}
    
}