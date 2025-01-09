package sistema_universidad.universidad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema_universidad.universidad.dto.CarreraDTO;
import sistema_universidad.universidad.model.Carrera;
import sistema_universidad.universidad.repository.CarreraRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarreraServiceImpl implements CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    @Override
    public List<CarreraDTO> mostrarCarrera(){
        List<Carrera> carreras = carreraRepository.findAll();
        return carreras.stream()
                .map(c -> new CarreraDTO(c.getId(), c.getNombre(), c.getDuracion()))
                .collect(Collectors.toList());
    }

    @Override
    public Carrera crearCarrera(Carrera carrera){
        return carreraRepository.save(carrera);
    }

    @Override
    public void eliminarCarrera(Integer id){
        carreraRepository.deleteById(id);
    }

    @Override
    public Carrera buscarPorId(Integer id){
        return carreraRepository.findById(id).orElse(null);
    }

    @Override
    public void editarCarrera(Carrera carrera) {
        // Verificar si la carrera con el ID dado existe
        Carrera carreraExistente = carreraRepository.findById(carrera.getId())
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        // Actualizar los campos necesarios de la carrera existente
        carreraExistente.setNombre(carrera.getNombre());
        carreraExistente.setDuracion(carrera.getDuracion());

        // Guardar los cambios
        carreraRepository.save(carreraExistente);
    }
}
