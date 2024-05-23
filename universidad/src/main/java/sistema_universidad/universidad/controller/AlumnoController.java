package sistema_universidad.universidad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sistema_universidad.universidad.dao.AlumnoDao;
import sistema_universidad.universidad.entity.Alumno;

@RestController
public class AlumnoController {

    @Autowired
    private AlumnoDao alumnoDao;

    @GetMapping("/universidad/alumnos")
    @ResponseBody
    public List<Alumno> getAlumnos() {
        return alumnoDao.getAlumnos();
    }

}
