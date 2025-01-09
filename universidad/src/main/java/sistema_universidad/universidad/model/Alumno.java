package sistema_universidad.universidad.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @Column(name = "nombre", length = 100)
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @Column(name = "apellido", length = 100)
    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;

    @Column(name = "dni", unique = true, nullable = false)
    @NotBlank(message = "El DNI no puede estar vacío")
    @Digits(integer = 8, fraction = 0, message = "El DNI debe ser un número de 8 dígitos")
    private String dni;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="carrera_id")
    @NotNull(message = "El alumno debe estar asociado a una carrera")
    private Carrera carrera;

    @Column(name = "telefono")
    @Pattern(regexp = "^[0-9]{10}$", message = "El teléfono debe tener 10 dígitos")
    private String telefono;

    @Column(name = "numeroLegajo", unique = true, nullable = false)
    @NotBlank(message = "El Legajo no puede estar vacío")
    @Size(min = 4, max = 10, message = "El Legajo debe tener entre 4 y 10 caracteres")
    private String numeroLegajo;

    @Column(name = "estado")
    @Pattern(regexp = "^(activo|inactivo)$", message = "El estado debe ser 'activo' o 'inactivo'")
    private String estado;
}