package sistema_universidad.universidad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistema_universidad.universidad.model.Carrera;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Integer> {

    Optional<Carrera> findByNombre(String nombre);
}
