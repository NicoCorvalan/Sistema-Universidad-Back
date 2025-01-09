package sistema_universidad.universidad.service;

import sistema_universidad.universidad.dto.AlumnoDTO;
import sistema_universidad.universidad.dto.CrearAlumnoDTO;
import sistema_universidad.universidad.model.Alumno;
import java.util.List;

public interface AlumnoService {
    List<AlumnoDTO> mostrarAlumnos();
    AlumnoDTO crearAlumno(CrearAlumnoDTO crearAlumnoDTO);
    void eliminarAlumno(Long id);
    AlumnoDTO buscarAlumnoPorId(Long id);
    AlumnoDTO editarAlumno(Long id,CrearAlumnoDTO crearAlumnoDTO);
}


