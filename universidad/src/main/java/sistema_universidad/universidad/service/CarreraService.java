package sistema_universidad.universidad.service;

import sistema_universidad.universidad.model.Carrera;
import sistema_universidad.universidad.dto.CarreraDTO;

import java.util.List;

public interface CarreraService {
    List<CarreraDTO> mostrarCarrera();
    Carrera crearCarrera(Carrera carrera);
    void eliminarCarrera(Long id);
    Carrera buscarPorId(Long id);
    void editarCarrera(Carrera carrera);
}
