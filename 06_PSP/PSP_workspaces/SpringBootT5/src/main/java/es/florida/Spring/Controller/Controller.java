package es.florida.Spring.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@RequestMapping("/hola")
	String home() {
		return "¡Hola Mundo!";
	}
	
	@RequestMapping("/adios")
	String bye() {
		return "¡Adios mundo!";
	}
	
	@GetMapping("/saludo")
	String saludo(@RequestParam(value="nombre") String strNombre) {
		return "¡Hola "+strNombre+"!";
	}
	
	@PostMapping("endpoint")
	ResponseEntity<Object> metodoEnpoint(@RequestBody String cuerpoPeticion){
		//En cuerpoPeticion se tiene el string del Body de la petición POST
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); //El método build se utiliza cuando no se devuelve contenido (Solo se envía al cliente un código)
	}
	
	//Otra manera de implementar la gestión de la petición GET
	@GetMapping("salu2")
	public ResponseEntity<String> salu2(@RequestParam(value = "nombre") String strNombre) {
		String respuesta = "Hola "+strNombre;
		//El string respuesta puede ser un HTML, un JSON, etc.
		return ResponseEntity.status(HttpStatus.OK).body(respuesta); //El método body() se utiliza que cuando sí se devuelve contenido  (además del código correspondiente)
	}
	
	

}
