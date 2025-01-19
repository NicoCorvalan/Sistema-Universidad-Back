package sistema_universidad.universidad.service;


import sistema_universidad.universidad.dto.CrearMateriaDTO;
import sistema_universidad.universidad.dto.MateriaDTO;


import java.util.List;

public interface MateriaService {
    List<MateriaDTO> mostrarMaterias();
    MateriaDTO crearMateria(CrearMateriaDTO crearMateriaDTO);
    void eliminarMateria(Long id);
    MateriaDTO buscarMateriaPorId(Long id);
    MateriaDTO editarMateria(Long id, CrearMateriaDTO crearMateriaDTO);
}
