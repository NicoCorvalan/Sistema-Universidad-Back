package sistema_universidad.universidad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/universidad")
public class UsuarioController {

    @GetMapping("/usuarios")
    public String getUsuarios() {
        return "usuarios";
    }
    
}
