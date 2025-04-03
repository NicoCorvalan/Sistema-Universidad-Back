package sistema_universidad.universidad.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sistema_universidad.universidad.dto.RequestCarreraDTO;
import sistema_universidad.universidad.dto.ResponseCarreraDTO;
import sistema_universidad.universidad.model.Carrera;
import sistema_universidad.universidad.model.Materia;
import sistema_universidad.universidad.repository.CarreraRepository;
import sistema_universidad.universidad.repository.MateriaRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CarreraServiceImpl implements CarreraService {

    private final CarreraRepository carreraRepository;
    private final MateriaRepository materiaRepository;

    @Override
    @Transactional
    public ResponseCarreraDTO crearCarrera(RequestCarreraDTO requestCarreraDTO) {
        // Verificar si ya existe una carrera con el mismo nombre
        Optional<Carrera> carreraExistente = carreraRepository.findByNombre(requestCarreraDTO.getNombre());
        if (carreraExistente.isPresent()) {
            throw new RuntimeException("Ya existe una carrera con el nombre: " + requestCarreraDTO.getNombre());
        }

        // Si materiasIds es null, usamos una lista vacía
        List<Materia> materias = (requestCarreraDTO.getMateriasIds() != null)
                ? requestCarreraDTO.getMateriasIds().stream()
                .map(id -> materiaRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Materia con ID " + id + " no encontrada")))
                .toList()
                : Collections.emptyList();

        // Crear la carrera
        Carrera carrera = new Carrera();
        carrera.setNombre(requestCarreraDTO.getNombre());
        carrera.setDuracion(requestCarreraDTO.getDuracion());

        // Establecer las relaciones entre la carrera y las materias
        for (Materia materia : materias) {
            carrera.getMaterias().add(materia);
            materia.getCarreras().add(carrera);
        }

        // Guardar la nueva carrera
        Carrera nuevaCarrera = carreraRepository.save(carrera);

        // Crear y devolver el CarreraDTO de forma manual
        return new ResponseCarreraDTO(
                nuevaCarrera.getId(),
                nuevaCarrera.getNombre(),
                nuevaCarrera.getDuracion()
        );
    }


    @Override
    @Transactional
    public ResponseCarreraDTO editarCarrera(Integer id, RequestCarreraDTO requestCarreraDTO) {
        Carrera carreraExistente = carreraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        // Actualizar campos básicos
        carreraExistente.setNombre(requestCarreraDTO.getNombre());
        carreraExistente.setDuracion(requestCarreraDTO.getDuracion());

        // Actualizar las materias asociadas
        if (requestCarreraDTO.getMateriasIds() != null && !requestCarreraDTO.getMateriasIds().isEmpty()) {
            // Obtener las nuevas materias validando individualmente
            List<Materia> nuevasMaterias = requestCarreraDTO.getMateriasIds().stream()
                    .map(materiaId -> materiaRepository.findById(materiaId)
                            .orElseThrow(() -> new RuntimeException("Materia con ID " + materiaId + " no encontrada")))
                    .toList();


            // Limpiar relaciones existentes
            carreraExistente.getMaterias().forEach(materia -> materia.getCarreras().remove(carreraExistente));
            carreraExistente.getMaterias().clear();

            // Asociar nuevas materias
            carreraExistente.setMaterias(nuevasMaterias);
            nuevasMaterias.forEach(materia -> materia.getCarreras().add(carreraExistente));
        }

        // Guardar los cambios
        Carrera carreraActualizada = carreraRepository.save(carreraExistente);

        // Crear y devolver el CarreraDTO de forma manual
        return new ResponseCarreraDTO(
                carreraActualizada.getId(),
                carreraActualizada.getNombre(),
                carreraActualizada.getDuracion()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseCarreraDTO> mostrarCarrera() {
        List<Carrera> carreras = carreraRepository.findAll();
        return carreras.stream()
                .map(c -> new ResponseCarreraDTO(
                        c.getId(),
                        c.getNombre(),
                        c.getDuracion()
                ))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void eliminarCarrera(Integer id) {
        carreraRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Carrera buscarPorId(Integer id) {
        return carreraRepository.findById(id).orElse(null);
    }
}