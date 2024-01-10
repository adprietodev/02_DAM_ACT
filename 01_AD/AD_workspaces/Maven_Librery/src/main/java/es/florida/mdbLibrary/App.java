package es.florida.mdbLibrary;

import static com.mongodb.client.model.Filters.*;

import java.util.Scanner;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class App {

	// Conexión
	private MongoClient mongoClient = new MongoClient("localhost", 27017);
	private MongoDatabase database = mongoClient.getDatabase("Biblioteca");
	private MongoCollection<Document> collection = database.getCollection("Libros");
	private boolean exit = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		App app = new App();

		while (!app.exit) {
			app.selectAction();
		}
		
		System.out.println("=============================================================");
		System.out.println("		¡Hasta la próxima!	");
		System.out.println("=============================================================");

	}

	private void selectAction() {

		Scanner kB = new Scanner(System.in);
		String idSelected = "";

		System.out.println("=============================================================");
		System.out.println("	Dime que acción quieres tomar.	");
		System.out.println("=============================================================\n");
		System.out.println("1. - Mostrar libros");
		System.out.println("2. - Información de libro.");
		System.out.println("3. - Añadir un libro.");
		System.out.println("4. - Modificar un libro.");
		System.out.println("5. - Eliminar un libro");
		System.out.println("6. - Salir.");

		String selection = kB.nextLine();

		switch (selection) {
		case "1":
			showAllBooks();
			break;
		case "2":
			System.out.println("Indicame la id del libro.");
			idSelected = kB.nextLine();
			showInfoBook(Integer.parseInt(idSelected));
			break;
		case "3":
			addBook();
			break;
		case "4":
			System.out.println("Indicame la id del libro.");
			idSelected = kB.nextLine();
			modifyBook(Integer.parseInt(idSelected));
			break;
		case "5":
			System.out.println("Indicame la id del libro.");
			idSelected = kB.nextLine();
			deleteBook(Integer.parseInt(idSelected));
			break;
		case "6":
			exitConnection();
			break;
		default:
			System.out.println("=============================================================");
			System.out.println("	No has elegido una opción valida.	");
			System.out.println("=============================================================\n");
			break;
		}
	}

	private int getLastId() {
		int lastId = 0;
		MongoCursor<Document> cursor = collection.find().iterator();

		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			lastId = obj.getInt("Id");
		}
		return lastId;
	}

	private void showAllBooks() {

		MongoCursor<Document> cursor = collection.find().iterator();
		System.out.println("ID - NOMBRE");
		while (cursor.hasNext()) {
			// System.out.println(cursor.next().toJson());
			// Parser JSON
			JSONObject obj = new JSONObject(cursor.next().toJson());
			System.out.println(obj.getInt("Id") + " - " + obj.getString("Titulo"));
		}
	}

	private void showInfoBook(int id) {

		Bson query = eq("Id", id);

		MongoCursor<Document> cursor = collection.find(query).iterator();

		JSONObject obj = new JSONObject(cursor.next().toJson());
		System.out.println("Información del libro.");
		System.out.println("ID Mongo: " + obj.getJSONObject("_id").getString("$oid"));
		System.out.println("ID: " + obj.getInt("Id"));
		System.out.println("Titulo: " + obj.getString("Titulo"));
		System.out.println("Autor: " + obj.getString("Autor"));
		if (obj.has("Anyo_nacimiento")) {
	        System.out.println("Año nacimiento: " + obj.getInt("Anyo_nacimiento"));
	    } else {
	        System.out.println("Año de nacimiento no disponible en el documento.");
	    }
		System.out.println("Año publicacion: " + obj.getInt("Anyo_publicacion"));
		System.out.println("Editorial: " + obj.getString("Editorial"));
		System.out.println("Numero paginas: " + obj.getInt("Numero_paginas"));
	}

	private void addBook() {

		Scanner kB = new Scanner(System.in);

		Document doc = new Document();
		String data = "";

		doc.append("Id", getLastId() + 1);

		System.out.println("Indicame el titulo:");
		data = kB.nextLine();
		doc.append("Titulo", data);

		System.out.println("Indicame el autor:");
		data = kB.nextLine();
		doc.append("Autor", data);

		System.out.println("Indicame el año nacimiento:");
		data = kB.nextLine();
		doc.append("Anyo_nacimiento", Integer.parseInt(data));

		System.out.println("Indicame el año publicación:");
		data = kB.nextLine();
		doc.append("Anyo_publicacion", Integer.parseInt(data));

		System.out.println("Indicame el editorial:");
		data = kB.nextLine();
		doc.append("Editorial", data);

		System.out.println("Indicame el numero paginas:");
		data = kB.nextLine();
		doc.append("Numero_paginas", Integer.parseInt(data));

		collection.insertOne(doc);

	}

	private void modifyBook(int id) {

		Scanner kB = new Scanner(System.in);
		boolean change = false;
		String data = "";

		Bson query = eq("Id", id);

		while (!change) {
			System.out.println("Indicame que apartado quieres modificar: ");
			System.out.println("1. - Titulo");
			System.out.println("2. - Autor");
			System.out.println("3. - Año nacimiento");
			System.out.println("4. - Año publicacion");
			System.out.println("5. - Editorial");
			System.out.println("6. - Numero paginas");

			String selection = kB.nextLine();

			switch (selection) {
			case "1":
				System.out.println("Indicame el titulo que quieres poner: ");
				data = kB.nextLine();
				collection.updateOne(query, new Document("$set", new Document("Titulo", data)));
				change = true;
				break;
			case "2":
				System.out.println("Indicame el autor que quieres poner: ");
				data = kB.nextLine();
				collection.updateOne(query, new Document("$set", new Document("Autor", data)));
				change = true;
				break;
			case "3":
				System.out.println("Indicame el año nacimiento que quieres poner: ");
				data = kB.nextLine();
				collection.updateOne(query, new Document("$set", new Document("Anyo_nacimiento", Integer.parseInt(data))));
				change = true;
				break;
			case "4":
				System.out.println("Indicame el año publicacion que quieres poner: ");
				data = kB.nextLine();
				collection.updateOne(query, new Document("$set", new Document("Anyo_publicacion", Integer.parseInt(data))));
				change = true;
				break;
			case "5":
				System.out.println("Indicame el editorial que quieres poner: ");
				data = kB.nextLine();
				collection.updateOne(query, new Document("$set", new Document("Editorial", data)));
				change = true;
				break;
			case "6":
				System.out.println("Indicame el numero de paginas que quieres poner: ");
				data = kB.nextLine();
				collection.updateOne(query, new Document("$set", new Document("Numero_paginas", Integer.parseInt(data))));
				change = true;
				break;
			default:
				System.out.println("=============================================================");
				System.out.println("	No has elegido una opción valida.	");
				System.out.println("=============================================================\n");
				break;
			}
		}

	}

	private void deleteBook(int id) {
		Bson query = eq("Id", id);

		collection.deleteOne(query);
	}

	private void exitConnection() {
		mongoClient.close();
		exit = true;
	}

}
