package sistema_universidad.universidad.service;

import sistema_universidad.universidad.model.Carrera;
import sistema_universidad.universidad.dto.CarreraDTO;

import java.util.List;

public interface CarreraService {
    List<CarreraDTO> mostrarCarrera();
    Carrera crearCarrera(Carrera carrera);
    void eliminarCarrera(Integer id);
    Carrera buscarPorId(Integer id);
    void editarCarrera(Carrera carrera);
}
