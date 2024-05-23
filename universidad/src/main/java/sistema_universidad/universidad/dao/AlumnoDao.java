package sistema_universidad.universidad.dao;
import sistema_universidad.universidad.entity.Alumno;

import java.util.List;


public interface AlumnoDao {
    List<Alumno> getAlumnos();
    void save(Alumno alumno);
    void update(Alumno alumno);
    void delete(Long id);
}