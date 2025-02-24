package sistema_universidad.universidad.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema_universidad.universidad.dto.CarreraDTO;
import sistema_universidad.universidad.dto.CrearCarreraDTO;
import sistema_universidad.universidad.model.Carrera;
import sistema_universidad.universidad.model.Materia;
import sistema_universidad.universidad.repository.CarreraRepository;
import sistema_universidad.universidad.repository.MateriaRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CarreraServiceImpl implements CarreraService {

    private final CarreraRepository carreraRepository;
    private  final MateriaRepository materiaRepository;

    @Override
    public CarreraDTO crearCarrera(CrearCarreraDTO crearCarreraDTO) {
        // Buscar todas las materias relacionadas por sus IDs
        List<Materia> materias = materiaRepository.findAllById(crearCarreraDTO.getMateriasIds());

        // Validar que se encontraron todas las materias
        if (materias.size() != crearCarreraDTO.getMateriasIds().size()) {
            throw new RuntimeException("Una o más materias no fueron encontradas");
        }

        // Crear la carrera
        Carrera carrera = new Carrera();
        carrera.setNombre(crearCarreraDTO.getNombre());
        carrera.setDuracion(crearCarreraDTO.getDuracion());

        // Establecer las relaciones entre la carrera y las materias
        for (Materia materia : materias) {
            carrera.getMaterias().add(materia);
            materia.getCarreras().add(carrera);
        }

        // Guardar la nueva carrera
        Carrera nuevaCarrera = carreraRepository.save(carrera);

        return new CarreraDTO(nuevaCarrera.getId(), nuevaCarrera.getNombre(), nuevaCarrera.getDuracion());
    }

    @Override
    public CarreraDTO editarCarrera(Integer id, CrearCarreraDTO crearCarreraDTO) {
        Carrera carreraExistente = carreraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        // Actualizar campos básicos
        carreraExistente.setNombre(crearCarreraDTO.getNombre());
        carreraExistente.setDuracion(crearCarreraDTO.getDuracion());

        // Actualizar las materias asociadas
        if (crearCarreraDTO.getMateriasIds() != null && !crearCarreraDTO.getMateriasIds().isEmpty()) {
            List<Materia> nuevasMaterias = materiaRepository.findAllById(crearCarreraDTO.getMateriasIds());
            if (nuevasMaterias.size() != crearCarreraDTO.getMateriasIds().size()) {
                throw new RuntimeException("Una o más materias no fueron encontradas");
            }

            // Limpiar relaciones existentes
            carreraExistente.getMaterias().forEach(materia -> materia.getCarreras().remove(carreraExistente));
            carreraExistente.getMaterias().clear();

            // Asociar nuevas materias
            carreraExistente.setMaterias(nuevasMaterias);
            nuevasMaterias.forEach(materia -> materia.getCarreras().add(carreraExistente));
        }

        // Guardar los cambios
        Carrera carreraActualizada = carreraRepository.save(carreraExistente);

        return new CarreraDTO(carreraActualizada.getId(), carreraActualizada.getNombre(), carreraActualizada.getDuracion());
    }



    @Override
    public List<CarreraDTO> mostrarCarrera(){
        List<Carrera> carreras = carreraRepository.findAll();
        return carreras.stream()
                .map(c -> new CarreraDTO(c.getId(), c.getNombre(), c.getDuracion()))
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarCarrera(Integer id){
        carreraRepository.deleteById(id);
    }

    @Override
    public Carrera buscarPorId(Integer id){
        return carreraRepository.findById(id).orElse(null);
    }


}
