package sistema_universidad.universidad.controller;

import java.util.List;

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
import sistema_universidad.universidad.service.AlumnoService;


@RestController
@RequestMapping("/universidad/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public List<Alumno> mostrarAlumnos() {
        return alumnoService.mostrarAlumnos();
    }

    @GetMapping("/{id}")
    public Alumno buscarPorId(@PathVariable Long id) {
        return alumnoService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<?> crearAlumno(@RequestBody Alumno alumno) {
        alumnoService.crearAlumno(alumno);
        return new ResponseEntity<>("Alumno creado exitosamente", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> editarAlumno(@RequestBody Alumno alumno){
        alumnoService.editarAlumno(alumno);
        return new ResponseEntity<>("Alumno modificado exitosamente", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAlumno(@PathVariable Long id) {
        alumnoService.eliminarAlumno(id);
        return new ResponseEntity<>("Alumno eliminado satisfactoriamente", HttpStatus.OK);
    }
    
}
