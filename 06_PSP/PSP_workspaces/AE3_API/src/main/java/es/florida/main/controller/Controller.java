package es.florida.main.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.florida.main.objects.JSONPostFilms;
import es.florida.main.services.FilterExtension;
import es.florida.main.services.JSONConverter;


@RestController
public class Controller {
	
	
	private String[] files;
	private List<String> authorizedUsersList;

	public Controller() {
		files = null;
		authorizedUsersList = new ArrayList();
	}
	
	
	/**
	 * Metodo que utilizamos para coger el nombre de los archivos y guardarlos.
	 */
	private void getFiles() {
		File dir = new File("pelis");
		files = dir.list(new FilterExtension(".txt"));
		Arrays.sort(files);
	}
	
	
	/**
	 * Metodo que utilizamos para coger el contenido de las peliculas, en caso de coger todos los datos o solo una id hacemos una cosa u otra
	 * @param strId es el id que estamos buscando en caso de ser todos sera (all)
	 * @return retornamos el JSON en string para mostrar por pantalla
	 */
	@GetMapping("/APIpelis/t")
	String getPelis(@RequestParam(value="id") String strId) {
		getFiles();
		JSONConverter jsonConverter = new JSONConverter();
		
		String strJSON = "";
		if(strId.equals("all")) {
			jsonConverter = new JSONConverter(files);
			strJSON = jsonConverter.getAllData();
		} else {
			
			if(new File("pelis/"+strId+".txt").exists()) {
				strJSON = jsonConverter.getOneTitle(strId+".txt");
			} else {
				strJSON = HttpStatus.NOT_FOUND.toString();
			}
			
		}
		
		return strJSON;
		
	}
	
	/**
	 * Metodo que utilizamos para añadir nuevas reseñas en la pelicula.
	 * @param cuerpoPeticion
	 * @return
	 */
	@PostMapping("/APIpelis/nuevaResenya")
	ResponseEntity<Object> newReview(@RequestBody String cuerpoPeticion){
		
		if(authorizedUsersList.size() == 0) {
			authorizedUsersList = authorizedUsers();
		}
		
		JSONConverter jsonConverter = new JSONConverter();
		JSONPostFilms postJSON = new JSONPostFilms();
		
		postJSON = jsonConverter.readJSON(cuerpoPeticion, "nuevaResenya");
		
		
		if(writeReview(postJSON, authorizedUsersList.contains(postJSON.getUser()))) {
			if(authorizedUsersList.contains(postJSON.getUser())) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
			
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	
	/**
	 * Metodo que utilizamos para generar una nueva pelicula.
	 * @param cuerpoPeticion
	 * @return
	 */
	@PostMapping("/APIpelis/nuevaPeli")
	ResponseEntity<Object> newFilm(@RequestBody String cuerpoPeticion){
		
		if(authorizedUsersList.size() == 0) {
			authorizedUsersList = authorizedUsers();
		}
		
		JSONConverter jsonConverter = new JSONConverter();
		JSONPostFilms postJSON = new JSONPostFilms();
		
		postJSON = jsonConverter.readJSON(cuerpoPeticion, "nuevaPeli");
		
		if(addFilm(postJSON.getTitle(), authorizedUsersList.contains(postJSON.getUser()))) {
			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
	}
	
	/**
	 * Metodo que utilizamos para añadir un nuevo usuario en la lista de autorizados
	 * @param cuerpoPeticion
	 * @return
	 */
	@PostMapping("/APIpelis/nuevoUsuario")
	ResponseEntity<Object> newUser(@RequestBody String cuerpoPeticion){
		JSONConverter jsonConverter = new JSONConverter();
		JSONPostFilms postJSON = new JSONPostFilms();
		
		postJSON = jsonConverter.readJSON(cuerpoPeticion, "nuevoUsuario");
		
		if(addAuthorizedUsers(postJSON.getUser())) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	
	/**
	 * Metodo que urilizamos para escribir la reseña dentro de la pelicula.
	 * @param postJSON le pasamos el objeto creado con los datos del post para escribir la reseña
	 * @param authorized le pasamos si el usuario puesto esta autorizado o no.
	 * @return retornamos si se ha realizado.
	 */
	private boolean writeReview(JSONPostFilms postJSON, boolean authorized) {
		
		File file = new File("pelis/"+postJSON.getId()+".txt");
		if(file.exists() && authorized) {
			try {
				FileWriter fW = new FileWriter(file, true);
				fW.write("\n"+postJSON.getUser()+": "+postJSON.getReview());
				fW.close();
				return true;
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	
	/**
	 * Metodo que utilizamos para añadir peliculas
	 * @param film el nombre de la pelicula que queremos añadir
	 * @param authorized si el usuario esta autorizado.
	 * @return retornamos si ha sido posible o no
	 */
	private boolean addFilm(String film, boolean authorized) {
		getFiles();
		
		
		try {
			if(authorized)	{
				String lastId = files[files.length-1].split("\\.")[0];
				String id = String.valueOf(Integer.parseInt(lastId) +1);
				
				File file = new File("pelis/"+id+".txt");
				file.createNewFile();
				while(!file.exists()) {
					
				}
				FileWriter fW = new FileWriter(file, true);
				fW.write("Titulo: "+film);
				fW.close();
				
				return true;
			} else {
				return false;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * Metodo que utilizamos para añadir usuarios en la lista de autorizados.
	 * @param user usuario que queremos añadir
	 * @return retornamos si ha sido posible o no
	 */
	private boolean addAuthorizedUsers(String user) {
		authorizedUsersList = authorizedUsers();
		authorizedUsersList.add(user);
		
		try {
			FileWriter fW = new FileWriter(new File("autorizados.txt"), true);
			fW.write("\n"+user);
			fW.close();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	 * Metodo que utilizamos para generar la lista de autorizados
	 * @return devolvemos lista de usuarios autorizados.
	 */
	private List<String> authorizedUsers(){
		
		List<String> auxList = new ArrayList();
		try {
			
			File file = new File("autorizados.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				auxList.add(line);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return auxList;

		
	}
	
	
}
