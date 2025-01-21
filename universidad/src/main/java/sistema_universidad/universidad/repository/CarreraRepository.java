package sistema_universidad.universidad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistema_universidad.universidad.model.Carrera;

import java.util.List;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Integer> {
    public List<Carrera> findAllByIdIn(List<Integer> ids);

}
