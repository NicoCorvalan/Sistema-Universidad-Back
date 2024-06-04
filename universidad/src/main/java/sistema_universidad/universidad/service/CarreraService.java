package sistema_universidad.universidad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema_universidad.universidad.model.Carrera;
import sistema_universidad.universidad.repository.CarreraRepository;

import java.util.List;

@Service
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    public List<Carrera> mostrarCarrera(){
        return carreraRepository.findAll();
    }

    public Carrera crearCarrera(Carrera carrera){
        return carreraRepository.save(carrera);
    }

    public void eliminarCarrera(Long id){
        carreraRepository.deleteById(id);
    }

    public Carrera buscarPorId(Long id){
        return carreraRepository.findById(id).orElse(null);
    }

    public void editarCarrera(Carrera carrera) {
        carreraRepository.save(carrera);
    }
}
