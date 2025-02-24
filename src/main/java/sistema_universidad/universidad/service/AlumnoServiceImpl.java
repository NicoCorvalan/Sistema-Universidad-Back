package sistema_universidad.universidad.service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import sistema_universidad.universidad.dto.AlumnoDTO;
import sistema_universidad.universidad.dto.CrearAlumnoDTO;
import sistema_universidad.universidad.model.Alumno;
import sistema_universidad.universidad.model.Carrera;
import sistema_universidad.universidad.repository.AlumnoRepository;
import sistema_universidad.universidad.repository.CarreraRepository;

@Service
@RequiredArgsConstructor
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;
    private final CarreraRepository carreraRepository;  // Agregar el repositorio de Carrera

    @Override
    public AlumnoDTO crearAlumno(CrearAlumnoDTO crearAlumnoDTO) {
        // Buscar carrera por ID
        Carrera carrera = carreraRepository.findById(crearAlumnoDTO.getCarreraId())
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        // Crear un nuevo objeto Alumno
        Alumno alumno = new Alumno();
        alumno.setNombre(crearAlumnoDTO.getNombre());
        alumno.setApellido(crearAlumnoDTO.getApellido());
        alumno.setDni( crearAlumnoDTO.getDni());
        alumno.setCarrera( carrera );
        alumno.setTelefono(crearAlumnoDTO.getTelefono());
        alumno.setNumeroLegajo(crearAlumnoDTO.getNumeroLegajo());
        alumno.setEstado(crearAlumnoDTO.getEstado());

        // Guardar el nuevo alumno en la base de datos
        Alumno nuevoAlumno = alumnoRepository.save(alumno);

        // Convierte la entidad Alumno guardada a AlumnoDTO y la devuelve
        return convertirAAlumnoDTO(nuevoAlumno);
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

    @Override
    public List<AlumnoDTO> mostrarAlumnos() {
        List<Alumno> alumnos = alumnoRepository.findAll();
        return alumnos.stream().map(this::convertirAAlumnoDTO).collect(Collectors.toList());
    }

    @Override
    public AlumnoDTO buscarAlumnoPorId(Long id){
        Alumno alumno = alumnoRepository.findById(id).orElse(null);
        if (alumno != null) {
            return convertirAAlumnoDTO(alumno);
        }else {
            return null;
        }
    }
    @Override
    public AlumnoDTO editarAlumno(Long id, CrearAlumnoDTO crearAlumnoDTO) {
        Alumno alumnoExistente = alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        // Actualizar campos del alumno
        alumnoExistente.setNombre(crearAlumnoDTO.getNombre());
        alumnoExistente.setApellido(crearAlumnoDTO.getApellido());
        alumnoExistente.setDni(crearAlumnoDTO.getDni());
        alumnoExistente.setTelefono(crearAlumnoDTO.getTelefono());
        alumnoExistente.setNumeroLegajo(crearAlumnoDTO.getNumeroLegajo());
        alumnoExistente.setEstado(crearAlumnoDTO.getEstado());

        // Actualizar carrera si se especificó
        if (crearAlumnoDTO.getCarreraId() != null) {
            Carrera carrera = carreraRepository.findById(crearAlumnoDTO.getCarreraId())
                    .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));
            alumnoExistente.setCarrera(carrera);
        }

        // Guardar cambios
        Alumno actualizado = alumnoRepository.save(alumnoExistente);

        // Retornar el DTO actualizado
        return convertirAAlumnoDTO(actualizado);
    }

}

