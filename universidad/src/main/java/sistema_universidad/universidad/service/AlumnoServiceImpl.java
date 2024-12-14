package sistema_universidad.universidad.service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import sistema_universidad.universidad.dto.AlumnoDTO;
import sistema_universidad.universidad.model.Alumno;
import sistema_universidad.universidad.model.Carrera;
import sistema_universidad.universidad.repository.AlumnoRepository;
import sistema_universidad.universidad.repository.CarreraRepository;

@Service
@RequiredArgsConstructor
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;
    private final CarreraRepository carreraRepository;  // Agregar el repositorio de Carrera

    public AlumnoDTO crearAlumno(Alumno alumno) {
        // Obtener la carrera desde la base de datos usando el ID
        Carrera carrera = carreraRepository.findById(alumno.getCarrera().getId())
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        // Asignar la carrera al alumno
        alumno.setCarrera(carrera);

        // Guardar el nuevo alumno en la base de datos
        Alumno nuevoAlumno = alumnoRepository.save(alumno);

        // Convierte la entidad Alumno guardada a AlumnoDTO y la devuelve
        return convertirAAlumnoDTO(nuevoAlumno);
    }
    public List<AlumnoDTO> mostrarAlumnos() {
        List<Alumno> alumnos = alumnoRepository.findAll();
        return alumnos.stream().map(this::convertirAAlumnoDTO).collect(Collectors.toList());
    }

    private AlumnoDTO convertirAAlumnoDTO(Alumno alumno) {
        return new AlumnoDTO(
                alumno.getId(),
                alumno.getNombre(),
                alumno.getApellido(),
                alumno.getDni(),
                alumno.getCarrera().getNombre(), // Aquí se obtiene el nombre de la carrera
                alumno.getTelefono(),
                alumno.getNumeroLegajo(),
                alumno.getEstado()
        );
    }

    public AlumnoDTO buscarAlumnoPorId(Long id){
        Alumno alumno = alumnoRepository.findById(id).orElse(null);
        if (alumno != null) {
            return convertirAAlumnoDTO(alumno);
        }else {
            return null;
        }
    }

    public void eliminarAlumno(Long id){
        alumnoRepository.deleteById(id);
    }

    public void editarAlumno(Alumno alumno) {
        alumnoRepository.save(alumno);
    }

}

