package sistema_universidad.universidad.service;


import sistema_universidad.universidad.dto.CrearMateriaDTO;
import sistema_universidad.universidad.dto.MateriaDTO;


import java.util.List;
import java.util.Optional;

public interface MateriaService {
    List<MateriaDTO> mostrarMaterias();
    MateriaDTO crearMateria(CrearMateriaDTO crearMateriaDTO);
    boolean eliminarMateria(Long id);
    Optional <MateriaDTO> buscarMateriaPorId(Long id);
    MateriaDTO editarMateria(Long id, CrearMateriaDTO crearMateriaDTO);
}
