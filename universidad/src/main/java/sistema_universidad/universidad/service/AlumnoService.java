package sistema_universidad.universidad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistema_universidad.universidad.model.Alumno;
import sistema_universidad.universidad.repository.AlumnoRepository;

@Service
public class AlumnoService {
    
    @Autowired
    private AlumnoRepository alumnoRepository;

    public List<Alumno> mostrarAlumnos(){
        return alumnoRepository.findAll();
    }

    public Alumno crearAlumno(Alumno alumno){
        return alumnoRepository.save(alumno);
    }

    public void eliminarAlumno(Long id){
        alumnoRepository.deleteById(id);
    }

    public Alumno buscarPorId(Long id){
        return alumnoRepository.findById(id).orElse(null);
    }

    public void editarAlumno(Alumno alumno) {
        alumnoRepository.save(alumno);
    }

}
