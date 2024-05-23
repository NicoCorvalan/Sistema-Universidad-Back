package sistema_universidad.universidad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/universidad")
public class HomeController {

    @GetMapping("/home")
    public String getHome() {
        return "home";
    }
}
