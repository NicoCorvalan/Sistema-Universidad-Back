package sistema_universidad.universidad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/universidad")
public class MateriaController {

    @GetMapping("/materias")
    public String getMaterias() {
        return "materias";
    }
}
