package sistema_universidad.universidad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sistema_universidad.universidad.model.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
  
    

}
