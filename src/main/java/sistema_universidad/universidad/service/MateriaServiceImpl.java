package sistema_universidad.universidad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistema_universidad.universidad.dto.CrearMateriaDTO;
import sistema_universidad.universidad.dto.MateriaDTO;
import sistema_universidad.universidad.model.Carrera;
import sistema_universidad.universidad.model.Materia;
import sistema_universidad.universidad.repository.CarreraRepository;
import sistema_universidad.universidad.repository.MateriaRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MateriaServiceImpl implements MateriaService{

    private final MateriaRepository materiaRepository;
    private final CarreraRepository carreraRepository;

    @Override
    public MateriaDTO crearMateria(CrearMateriaDTO crearMateriaDTO) {
        // Buscar todas las carreras relacionadas por sus IDs
        List<Carrera> carreras = carreraRepository.findAllById(crearMateriaDTO.getCarreraIds());

        // Validar que se encontraron todas las carreras
        if (carreras.size() != crearMateriaDTO.getCarreraIds().size()) {
            throw new RuntimeException("Una o más carreras no fueron encontradas");
        }

        // Crear la materia
        Materia materia = new Materia();
        materia.setNombre(crearMateriaDTO.getNombre());
        materia.setHorasCursado(crearMateriaDTO.getHorasCursado());
        materia.setDuracion(crearMateriaDTO.getDuracion());

        // Establecer las relaciones entre la materia y las carreras
        for (Carrera carrera : carreras) {
            materia.getCarreras().add(carrera);
            carrera.getMaterias().add(materia);
        }

        // Guardar la nueva materia
        Materia nuevaMateria = materiaRepository.save(materia);

        return convertirAMateriaDTO(nuevaMateria);
    }


    private MateriaDTO convertirAMateriaDTO(Materia materia) {
        List<String> nombresCarreras = materia.getCarreras().stream()
                .map(Carrera::getNombre)
                .collect(Collectors.toList());

        return new MateriaDTO(
                materia.getId(),
                materia.getNombre(),
                nombresCarreras,
                materia.getDuracion(),
                materia.getHorasCursado()
        );
    }


    @Override
    public List<MateriaDTO> mostrarMaterias(){
        List<Materia> materias = materiaRepository.findAll();
        return materias.stream().map(this::convertirAMateriaDTO).collect(Collectors.toList());
    }

    @Override
    public MateriaDTO buscarMateriaPorId(Long id) {
        Materia materia = materiaRepository.findById(id).orElse(null);
        if (materia != null) {
            return convertirAMateriaDTO(materia);
        }
        return null; // Si no se encuentra la materia, devuelve null
    }

    @Override
    public void eliminarMateria(Long id){
        materiaRepository.deleteById(id);
    }

    @Override
    public MateriaDTO editarMateria(Long id, CrearMateriaDTO crearMateriaDTO) {
        Materia materiaExistente = materiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));

        // Actualizar campos básicos
        materiaExistente.setNombre(crearMateriaDTO.getNombre());
        materiaExistente.setHorasCursado(crearMateriaDTO.getHorasCursado());
        materiaExistente.setDuracion(crearMateriaDTO.getDuracion());

        // Actualizar carreras asociadas
        if (crearMateriaDTO.getCarreraIds() != null && !crearMateriaDTO.getCarreraIds().isEmpty()) {
            // Obtener las nuevas carreras por ID
            List<Carrera> nuevasCarreras = carreraRepository.findAllById(crearMateriaDTO.getCarreraIds());
            if (nuevasCarreras.size() != crearMateriaDTO.getCarreraIds().size()) {
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
