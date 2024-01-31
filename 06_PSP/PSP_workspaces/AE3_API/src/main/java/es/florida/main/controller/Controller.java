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
	
	private void getFiles() {
		File dir = new File("pelis");
		files = dir.list(new FilterExtension(".txt"));
		Arrays.sort(files);
	}
	
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
