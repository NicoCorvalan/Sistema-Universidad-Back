package sistema_universidad.universidad.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sistema_universidad.universidad.enums.Estado;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "alumnos")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "apellido", length = 20)
    private String apellido;

    @Column(name = "dni", unique = true, nullable = false)
    private String dni;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="carrera_id")
    private Carrera carrera;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "numeroLegajo", unique = true, nullable = false)
    private String numeroLegajo;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private Estado estado;

}