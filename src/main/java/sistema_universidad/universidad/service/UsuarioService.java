//package sistema_universidad.universidad.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import sistema_universidad.universidad.model.Usuario;
//import sistema_universidad.universidad.repository.UsuarioRepository;
//
//@Service
//public class UsuarioService {
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public Usuario registrarUsuario(String correo, String nombre, String password) {
//        String encryptedPassword = bCryptPasswordEncoder.encode(password);
//        Usuario usuario = new Usuario(null, nombre, correo, encryptedPassword);
//        return usuarioRepository.save(usuario);
//    }
//
//    public Usuario obtenerUsuarioPorCorreo(String correo) {
//        return usuarioRepository.findByCorreo(correo).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
//    }
//}
