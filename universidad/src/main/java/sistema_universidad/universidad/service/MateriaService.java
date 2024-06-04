package sistema_universidad.universidad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistema_universidad.universidad.model.Materia;
import sistema_universidad.universidad.repository.MateriaRepository;

import java.util.List;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    public List<Materia> mostrarMateria(){
        return materiaRepository.findAll();
    }

    public Materia crearMateria(Materia materia){
        return materiaRepository.save(materia);
    }

    public void eliminarMateria(Long id){
        materiaRepository.deleteById(id);
    }

    public Materia buscarPorId(Long id){
        return materiaRepository.findById(id).orElse(null);
    }

    public void editarMateria(Materia materia) {
        materiaRepository.save(materia);
    }
}
