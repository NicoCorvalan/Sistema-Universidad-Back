// package sistema_universidad.universidad.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import sistema_universidad.universidad.model.Usuario;
// import sistema_universidad.universidad.repository.UsuarioRepository;

// @Service
// public class UsuarioService {

//     @Autowired
//     private UsuarioRepository usuarioRepository;

//     public Usuario crearUsuario(Usuario usuario) {
//         return usuarioRepository.save(usuario);
//     }

//     public void eliminarUsuario(Long id){
//         usuarioRepository.deleteById(id);
//     }

//     public Usuario login(String nombre, String password){
//         return usuarioRepository.findByUserAndPassword(nombre, password);
//     }
// }
