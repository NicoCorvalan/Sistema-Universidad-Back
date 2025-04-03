package sistema_universidad.universidad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistema_universidad.universidad.dto.RequestMateriaDTO;
import sistema_universidad.universidad.dto.ResponseMateriaDTO;
import sistema_universidad.universidad.model.Carrera;
import sistema_universidad.universidad.model.Materia;
import sistema_universidad.universidad.repository.CarreraRepository;
import sistema_universidad.universidad.repository.MateriaRepository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MateriaServiceImpl implements MateriaService{

    private final MateriaRepository materiaRepository;
    private final CarreraRepository carreraRepository;

    @Override
    @Transactional
    public ResponseMateriaDTO crearMateria(RequestMateriaDTO requestMateriaDTO) {

        // Verificar si ya existe una materia con el mismo nombre
        Optional<Materia> materiaExistente = materiaRepository.findByNombre(requestMateriaDTO.getNombre());
        if (materiaExistente.isPresent()) {
            throw new RuntimeException("Ya existe una materia con el nombre: " + requestMateriaDTO.getNombre());
        }

        // Buscar todas las carreras relacionadas por sus IDs
        List<Carrera> carreras = carreraRepository.findAllById(requestMateriaDTO.getCarreraIds());

        // Validar que se encontraron todas las carreras
        if (carreras.size() != requestMateriaDTO.getCarreraIds().size()) {
            throw new RuntimeException("Una o más carreras no fueron encontradas");
        }

        // Crear la materia
        Materia materia = new Materia();
        materia.setNombre(requestMateriaDTO.getNombre());
        materia.setHorasCursado(requestMateriaDTO.getHorasCursado());
        materia.setDuracion(requestMateriaDTO.getDuracion());

        // Establecer las relaciones entre la materia y las carreras
        for (Carrera carrera : carreras) {
            materia.getCarreras().add(carrera);
            carrera.getMaterias().add(materia);
        }

        // Guardar la nueva materia
        Materia nuevaMateria = materiaRepository.save(materia);

        return convertirAMateriaDTO(nuevaMateria);
    }


    private ResponseMateriaDTO convertirAMateriaDTO(Materia materia) {
        List<String> nombresCarreras = materia.getCarreras().stream()
                .map(Carrera::getNombre)
                .collect(Collectors.toList());

        return ResponseMateriaDTO.builder()
                .id(materia.getId())
                .nombre(materia.getNombre())
                .carrera(nombresCarreras)
                .duracion(materia.getDuracion())
                .horasCursado(materia.getHorasCursado())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseMateriaDTO> mostrarMaterias(){
        List<Materia> materias = materiaRepository.findAll();
        return materias.stream().map(this::convertirAMateriaDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ResponseMateriaDTO> buscarMateriaPorId(Long id) {
        return materiaRepository.findById(id)
                .map(this::convertirAMateriaDTO);
    }

    @Override
    @Transactional
    public boolean eliminarMateria(Long id) {
        Optional<Materia> materiaOptional = materiaRepository.findById(id);

        if (materiaOptional.isPresent()) {
            Materia materia = materiaOptional.get();

            // Limpiamos las relaciones para mantener la consistencia
            materia.getCarreras().forEach(carrera -> carrera.getMaterias().remove(materia));
            materia.getCarreras().clear();

            materiaRepository.delete(materia);
            return true;
        }

        return false; // No encontrada
    }

    @Override
    @Transactional
    public ResponseMateriaDTO editarMateria(Long id, RequestMateriaDTO requestMateriaDTO) {
        Materia materiaExistente = materiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));

        // Actualizar campos básicos
        materiaExistente.setNombre(requestMateriaDTO.getNombre());
        materiaExistente.setHorasCursado(requestMateriaDTO.getHorasCursado());
        materiaExistente.setDuracion(requestMateriaDTO.getDuracion());

        // Actualizar carreras asociadas
        if (requestMateriaDTO.getCarreraIds() != null && !requestMateriaDTO.getCarreraIds().isEmpty()) {
            // Obtener las nuevas carreras por ID
            List<Carrera> nuevasCarreras = carreraRepository.findAllById(requestMateriaDTO.getCarreraIds());
            if (nuevasCarreras.size() != requestMateriaDTO.getCarreraIds().size()) {
                throw new RuntimeException("Una o más carreras no fueron encontradas");
            }

            // Limpiar relaciones existentes
            materiaExistente.getCarreras().forEach(carrera -> carrera.getMaterias().remove(materiaExistente));
            materiaExistente.getCarreras().clear();

            // Asociar nuevas carreras
            materiaExistente.setCarreras(nuevasCarreras);
            nuevasCarreras.forEach(carrera -> carrera.getMaterias().add(materiaExistente));
        }

        // Guardar los cambios
        Materia actualizado = materiaRepository.save(materiaExistente);

        return convertirAMateriaDTO(actualizado);
    }

}
