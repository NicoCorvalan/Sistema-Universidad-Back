package sistema_universidad.universidad.service;


import sistema_universidad.universidad.dto.AlumnoDTO;
import sistema_universidad.universidad.dto.MateriaDTO;
import sistema_universidad.universidad.model.Alumno;
import sistema_universidad.universidad.model.Materia;

import java.util.List;

public interface MateriaService {
    List<MateriaDTO> mostrarMateria();
    MateriaDTO crearMateria(Materia materia);
    void eliminarMateria(Long id);
    MateriaDTO buscarMateriaPorId(Long id);
    void editarMateria(Materia materia);
}
