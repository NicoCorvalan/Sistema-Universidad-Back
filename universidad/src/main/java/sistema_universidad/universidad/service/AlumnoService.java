package sistema_universidad.universidad.service;

import sistema_universidad.universidad.dto.AlumnoDTO;
import sistema_universidad.universidad.model.Alumno;
import java.util.List;

public interface AlumnoService {
    List<AlumnoDTO> mostrarAlumnos();
    AlumnoDTO crearAlumno(Alumno alumno);
    void eliminarAlumno(Long id);
    Alumno buscarPorId(Long id);
    void editarAlumno(Alumno alumno);
}


