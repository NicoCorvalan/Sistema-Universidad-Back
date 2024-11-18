package sistema_universidad.universidad;  // Asegúrate de que el paquete sea el correcto

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Permite solicitudes desde el frontend
                registry.addMapping("/**")  // Afecta a todas las rutas de la API
                        .allowedOrigins("http://localhost:5173")  // URL de tu frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE");  // Métodos permitidos
            }
        };
    }
}
