package sistema_universidad.universidad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sistema_universidad.universidad.model.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long>{
    
}
