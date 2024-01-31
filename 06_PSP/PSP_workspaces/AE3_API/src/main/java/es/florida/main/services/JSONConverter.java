package es.florida.main.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import es.florida.main.objects.*;

public class JSONConverter {

	private String[] files;

	public JSONConverter() {

	}

	public JSONConverter(String[] files) {
		this.files = files;
	}
	
	public JSONPostFilms readJSON(String fileJSON, String post) {
		System.out.println(fileJSON);
		JSONObject jsonObject = new JSONObject(fileJSON);
		JSONPostFilms postJSON = new JSONPostFilms();
		
		if(post.equals("nuevaResenya")) {
			String user = jsonObject.getString("usuario");
			String id = jsonObject.getString("id");
			String review = jsonObject.getString("resenya");
			
			postJSON = new JSONPostFilms(user,id,review);
		}
		
		if(post.equals("nuevaPeli")) {
			String user = jsonObject.getString("usuario");
			String title = jsonObject.getString("titulo");
			
			postJSON = new JSONPostFilms(user,title);
		}
		
		if(post.equals("nuevoUsuario")) {
			String user = jsonObject.getString("usuario");
			
			postJSON = new JSONPostFilms(user);
		}
		
		return postJSON;
	}

	public String getAllData() {
		try {
			JSONObject jsonObject = new JSONObject();
			JSONArray movieArray = new JSONArray();

			for (String file : files) {
				FileReader fr = new FileReader("pelis/" + file);
				BufferedReader br = new BufferedReader(fr);
				String id = file.split("\\.")[0];
				List<String> lines = new ArrayList<>();
				String line;

				while ((line = br.readLine()) != null) {
					lines.add(line);
				}

				JSONObject movieObject = convertToJSON(id, lines);
				movieArray.put(movieObject);

				// System.out.println(movieObject.toString());

				br.close();
			}

			jsonObject.put("titulos", movieArray);

			return jsonObject.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}

	public String getOneTitle(String nameFile) {
		try {
			JSONObject jsonObject = new JSONObject();
			FileReader fr = new FileReader("pelis/" + nameFile);
			BufferedReader br = new BufferedReader(fr);
			String id = nameFile.split("\\.")[0];
			List<String> lines = new ArrayList<>();
			String line;

			while ((line = br.readLine()) != null) {
				lines.add(line);
			}

			br.close();

			return convertToJSON(id, lines).toString();

		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}

	private JSONObject convertToJSON(String id, List<String> lines) {
		String title = "";

		title = lines.get(0).split(":")[1];

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", id);
		jsonObject.put("titulo", title);

		JSONArray reviewArray = new JSONArray();

		if (lines.size() > 1) {
			for (int i = 2; i < lines.size(); i++) {
				reviewArray.put(lines.get(i));
			}
		}

		jsonObject.put("resenyas", reviewArray);

		return jsonObject;
	}

}
