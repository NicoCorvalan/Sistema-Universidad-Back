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
@Table(name = "carreras")
public class Carrera{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
}