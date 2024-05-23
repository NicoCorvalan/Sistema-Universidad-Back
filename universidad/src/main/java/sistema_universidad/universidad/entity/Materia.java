package sistema_universidad.universidad.entity;


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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
}