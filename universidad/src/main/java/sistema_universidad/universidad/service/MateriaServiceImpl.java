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
    public MateriaDTO crearMateria(CrearMateriaDTO crearMateriaDTO){
        Carrera carrera = carreraRepository.findById(crearMateriaDTO.getCarreraId())
                .orElseThrow(() -> new RuntimeException("Carrrera no encontrada"));

        Materia materia = new Materia();
        materia.setNombre(crearMateriaDTO.getNombre());
        materia.setCarrera(carrera);
        materia.setHorasCursado(crearMateriaDTO.getHorasCursado());
        materia.setDuracion(crearMateriaDTO.getDuracion());

        Materia nuevoMateria = materiaRepository.save(materia);

        return convertirAMateriaDTO(nuevoMateria);
    }

    private MateriaDTO convertirAMateriaDTO( Materia materia){
        return new MateriaDTO(
                materia.getId(),
                materia.getNombre(),
                materia.getCarrera().getNombre(),
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

        materiaExistente.setNombre(crearMateriaDTO.getNombre());
        materiaExistente.setHorasCursado(crearMateriaDTO.getHorasCursado());
        materiaExistente.setDuracion(crearMateriaDTO.getDuracion());

        if (crearMateriaDTO.getCarreraId() != null) {

            Carrera carrera = carreraRepository.findById(crearMateriaDTO.getCarreraId())
                    .orElseThrow(() -> new RuntimeException("Carrrera no encontrada"));
            materiaExistente.setCarrera(carrera);
        }

        Materia actualizado = materiaRepository.save(materiaExistente);

        return convertirAMateriaDTO(actualizado);
    }
}
