package es.florida.main.controller;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ImagenesController {

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("/imagenes/{nombreImagen:.+}")
    public ResponseEntity<Resource> obtenerImagen(@PathVariable String nombreImagen) {
        try {
            // Cargar la imagen desde la carpeta "imgs" utilizando el ResourceLoader
        	Resource imagen = new FileSystemResource(new File("./imgs/"+nombreImagen));
            System.out.print(imagen.exists());
            // Verificar si la imagen existe
            if (imagen.exists()) {
            	MediaType mediaType = MediaType.IMAGE_JPEG;
                // Si la imagen existe, devolverla en la respuesta
            	return ResponseEntity.ok()
                        .contentType(mediaType)
                        .body(imagen);
            } else {
                // Si la imagen no existe, devolver un error 404
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir al cargar la imagen
            return ResponseEntity.status(500).build();
        }
    }
}