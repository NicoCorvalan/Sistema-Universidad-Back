package sistema_universidad.universidad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema_universidad.universidad.dto.MateriaDTO;
import sistema_universidad.universidad.model.Materia;
import sistema_universidad.universidad.repository.MateriaRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MateriaServiceImpl implements MateriaService{

    private final MateriaRepository materiaRepository;

    public List<MateriaDTO> mostrarMateria(){
        List<Materia> materias = materiaRepository.findAll();
        return materias.stream().map(this::convertirAMateriaDTO).collect(Collectors.toList());
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

    public MateriaDTO crearMateria(Materia materia){
        Materia nuevaMateria = materiaRepository.save(materia);
        return convertirAMateriaDTO(nuevaMateria);
    }
    public MateriaDTO buscarMateriaPorId(Long id) {
        Materia materia = materiaRepository.findById(id).orElse(null);
        if (materia != null) {
            return convertirAMateriaDTO(materia);
        }
        return null; // Si no se encuentra la materia, devuelve null
    }

    public void eliminarMateria(Long id){
        materiaRepository.deleteById(id);
    }

    public void editarMateria(Materia materia) {
        materiaRepository.save(materia);
    }
}
