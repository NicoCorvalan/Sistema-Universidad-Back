package sistema_universidad.universidad.service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import sistema_universidad.universidad.dto.AlumnoDTO;
import sistema_universidad.universidad.model.Alumno;
import sistema_universidad.universidad.repository.AlumnoRepository;

@Service
@RequiredArgsConstructor
public class AlumnoServiceImpl {

    private final AlumnoRepository alumnoRepository;

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

    public AlumnoDTO crearAlumno(Alumno alumno) {
        // Guarda el nuevo alumno en la base de datos
        Alumno nuevoAlumno = alumnoRepository.save(alumno);
        // Convierte la entidad Alumno guardada a AlumnoDTO y la devuelve
        return convertirAAlumnoDTO(nuevoAlumno);
    }


    public void eliminarAlumno(Long id){
        alumnoRepository.deleteById(id);
    }

    public Alumno buscarPorId(Long id){
        return alumnoRepository.findById(id).orElse(null);
    }

    public void editarAlumno(Alumno alumno) {
        alumnoRepository.save(alumno);
    }

}

