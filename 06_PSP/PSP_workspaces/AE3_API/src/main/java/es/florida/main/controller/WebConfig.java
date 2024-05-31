package es.florida.main.controller;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/imagenes/**")  // Define el patrón URL para las imágenes
            .addResourceLocations("classpath:/imgs/");  // Ruta de la carpeta que contiene las imágenes
    }
}
