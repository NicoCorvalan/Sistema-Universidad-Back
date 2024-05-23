package sistema_universidad.universidad.model;

import jakarta.persistence.*;
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
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "dni")
    private String dni;

    @Column(name = "carrera_id")
    private int carrera_id;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "numero_legajo")
    private String numero_legajo;
    
    @Column(name = "estado")
    private String estado;
}