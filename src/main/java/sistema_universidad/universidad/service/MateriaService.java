package sistema_universidad.universidad.service;


import sistema_universidad.universidad.dto.RequestMateriaDTO;
import sistema_universidad.universidad.dto.ResponseMateriaDTO;


import java.util.List;
import java.util.Optional;

public interface MateriaService {
    List<ResponseMateriaDTO> mostrarMaterias();
    ResponseMateriaDTO crearMateria(RequestMateriaDTO requestMateriaDTO);
    boolean eliminarMateria(Long id);
    Optional <ResponseMateriaDTO> buscarMateriaPorId(Long id);
    ResponseMateriaDTO editarMateria(Long id, RequestMateriaDTO requestMateriaDTO);
}
