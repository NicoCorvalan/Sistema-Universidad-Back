package sistema_universidad.universidad.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    @Column(name = "nombre")
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "dni", unique = true, nullable = false)
    @NotBlank(message = "El DNI no puede estar vacío")
    private String dni;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="carrera_id")
    private Carrera carrera;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "numeroLegajo", unique = true, nullable = false)
    @NotBlank(message = "El Legajo no puede estar vacío")
    private String numeroLegajo;

    @Column(name = "estado")
    private String estado;
}