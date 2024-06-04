package sistema_universidad.universidad.model;


import jakarta.persistence.*;
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

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "carrera_id")
    private Long carrera;

    @Enumerated(EnumType.STRING)
    @Column(name = "duracion")
    private Duracion duracion;

    @Column(name = "horas_cursado")
    private int horas_cursado;

    public enum Duracion {anual, cuatrimestral}
    
}