package sistema_universidad.universidad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sistema_universidad.universidad.dto.RequestAlumnoDTO;
import sistema_universidad.universidad.dto.ResponseAlumnoDTO;
import sistema_universidad.universidad.model.Alumno;
import sistema_universidad.universidad.model.Carrera;
import sistema_universidad.universidad.repository.AlumnoRepository;
import sistema_universidad.universidad.repository.CarreraRepository;
import sistema_universidad.universidad.mapper.AlumnoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;
    private final CarreraRepository carreraRepository;
    private final AlumnoMapper alumnoMapper;  // Inyectamos el Mapper

    @Override
    @Transactional
    public ResponseAlumnoDTO crearAlumno(RequestAlumnoDTO requestAlumnoDTO) {

        // Verifica si ya existe un alumno con el mismo DNI
        if (alumnoRepository.existsByDni(requestAlumnoDTO.getDni())) {
            throw new RuntimeException("Ya existe un alumno con el DNI " + requestAlumnoDTO.getDni());
        }

        // Buscar carrera por ID
        Carrera carrera = carreraRepository.findById(requestAlumnoDTO.getCarreraId())
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        // Crear un nuevo objeto Alumno
        Alumno alumno = new Alumno();
        alumno.setNombre(requestAlumnoDTO.getNombre());
        alumno.setApellido(requestAlumnoDTO.getApellido());
        alumno.setDni(requestAlumnoDTO.getDni());
        alumno.setCarrera(carrera);
        alumno.setTelefono(requestAlumnoDTO.getTelefono());
        alumno.setNumeroLegajo(requestAlumnoDTO.getNumeroLegajo());
        alumno.setEstado(requestAlumnoDTO.getEstado());

        // Guardar el nuevo alumno en la base de datos
        Alumno nuevoAlumno = alumnoRepository.save(alumno);

        // Usamos el Mapper para convertir la entidad Alumno a AlumnoDTO
        return alumnoMapper.alumnoToAlumnoDTO(nuevoAlumno);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseAlumnoDTO> mostrarAlumnos() {
        return alumnoRepository.findAll().stream()
                .map(alumnoMapper::alumnoToAlumnoDTO)  // Usamos el Mapper
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseAlumnoDTO buscarAlumnoPorId(Long id) {
        Alumno alumno = alumnoRepository.findById(id).orElse(null);
        if (alumno != null) {
            return alumnoMapper.alumnoToAlumnoDTO(alumno);  // Usamos el Mapper
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public ResponseAlumnoDTO editarAlumno(Long id, RequestAlumnoDTO requestAlumnoDTO) {
        Alumno alumnoExistente = alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        // Actualizamos los campos del alumno
        alumnoExistente.setNombre(requestAlumnoDTO.getNombre());
        alumnoExistente.setApellido(requestAlumnoDTO.getApellido());
        alumnoExistente.setDni(requestAlumnoDTO.getDni());
        alumnoExistente.setTelefono(requestAlumnoDTO.getTelefono());
        alumnoExistente.setNumeroLegajo(requestAlumnoDTO.getNumeroLegajo());
        alumnoExistente.setEstado(requestAlumnoDTO.getEstado());

        // Actualizar carrera si se especificó
        if (requestAlumnoDTO.getCarreraId() != null) {
            Carrera carrera = carreraRepository.findById(requestAlumnoDTO.getCarreraId())
                    .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));
            alumnoExistente.setCarrera(carrera);
        }

        // Guardamos los cambios
        Alumno actualizado = alumnoRepository.save(alumnoExistente);

        // Retornamos el DTO actualizado
        return alumnoMapper.alumnoToAlumnoDTO(actualizado);
    }
}
