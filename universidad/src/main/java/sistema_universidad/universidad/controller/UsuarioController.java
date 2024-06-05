// package sistema_universidad.universidad.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestParam;

// import sistema_universidad.universidad.model.Usuario;
// import sistema_universidad.universidad.service.UsuarioService;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;


// @Controller
// @RequestMapping("/universidad/usuarios")
// public class UsuarioController {

//     @Autowired
//     private UsuarioService usuarioService;

//     @PostMapping
//     public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
//         usuarioService.crearUsuario(usuario);
//         return new ResponseEntity<>("Usuario creado con exito", HttpStatus.CREATED);
//     }

//     @DeleteMapping
//     public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
//         usuarioService.eliminarUsuario(id);
//         return new ResponseEntity<>("Usuario eliminado con exito", HttpStatus.OK);
//     }
    
//     @GetMapping("/login")
//     public Usuario login(@RequestParam Usuario usuario) {
//         return new Usuario();
//     }
// }
