package sistema_universidad.universidad.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sistema_universidad.universidad.model.Alumno;
import sistema_universidad.universidad.repository.AlumnoRepository;

@RestController
@RequestMapping("/universidad/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @GetMapping
    public List<Alumno> getAlumnos() {
        return alumnoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> saveAlumno(@RequestBody Alumno alumno) {
        alumnoRepository.save(alumno);
        return new ResponseEntity<>("Alumno creado exitosamente", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAlumno(@PathVariable Long id, @RequestBody Alumno updatedAlumno) {
        Optional<Alumno> alumnoOptional = alumnoRepository.findById(id);
        if (alumnoOptional.isPresent()) {
            Alumno alumno = alumnoOptional.get();
            alumno.setNombre(updatedAlumno.getNombre());
            alumno.setApellido(updatedAlumno.getApellido());
            // Actualizar los demás campos según sea necesario
            alumnoRepository.save(alumno);
            return new ResponseEntity<>("Alumno actualizado exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Alumno no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAlumno(@PathVariable Long id) {
        Optional<Alumno> alumnoOptional = alumnoRepository.findById(id);
        if (alumnoOptional.isPresent()) {
            alumnoRepository.deleteById(id);
            return new ResponseEntity<>("Alumno eliminado exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Alumno no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}
