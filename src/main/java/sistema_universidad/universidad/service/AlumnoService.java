package sistema_universidad.universidad.service;

import sistema_universidad.universidad.dto.ResponseAlumnoDTO;
import sistema_universidad.universidad.dto.RequestAlumnoDTO;
import java.util.List;

public interface AlumnoService {
    List<ResponseAlumnoDTO> mostrarAlumnos();
    ResponseAlumnoDTO crearAlumno(RequestAlumnoDTO crearAlumnoDTO);
    ResponseAlumnoDTO buscarAlumnoPorId(Long id);
    ResponseAlumnoDTO editarAlumno(Long id, RequestAlumnoDTO requestAlumnoDTO);
}


