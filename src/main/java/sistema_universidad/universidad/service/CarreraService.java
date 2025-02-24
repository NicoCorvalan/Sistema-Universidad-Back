package sistema_universidad.universidad.service;

import sistema_universidad.universidad.dto.CrearCarreraDTO;
import sistema_universidad.universidad.model.Carrera;
import sistema_universidad.universidad.dto.CarreraDTO;

import java.util.List;

public interface CarreraService {
    List<CarreraDTO> mostrarCarrera();
    CarreraDTO crearCarrera(CrearCarreraDTO crearCarreraDTO);
    void eliminarCarrera(Integer id);
    Carrera buscarPorId(Integer id);
    CarreraDTO editarCarrera(Integer id, CrearCarreraDTO crearCarreraDTO);
}
