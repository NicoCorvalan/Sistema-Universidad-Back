package sistema_universidad.universidad.service;

import sistema_universidad.universidad.dto.RequestCarreraDTO;
import sistema_universidad.universidad.dto.ResponseCarreraDTO;
import sistema_universidad.universidad.model.Carrera;

import java.util.List;

public interface CarreraService {
    List<ResponseCarreraDTO> mostrarCarrera();
    ResponseCarreraDTO crearCarrera(RequestCarreraDTO crearCarreraDTO);
    void eliminarCarrera(Integer id);
    Carrera buscarPorId(Integer id);
    ResponseCarreraDTO editarCarrera(Integer id, RequestCarreraDTO crearCarreraDTO);
}
