package es.florida.main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.apache.commons.codec.binary.Base64;
import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.*;

public class Main {
	private static List<String> titulos = new ArrayList<>();
	
	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("cine");
		MongoCollection<Document> coleccion = database.getCollection("peliculas");

		// CRUD operations

		MongoCursor<Document> cursor = coleccion.find().iterator();
		int num = 1;
		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			titulos.add(obj.getString("titulo"));
			System.out.println(num+".-"+obj.getString("titulo"));
			num++;
		}
		
		showInfoFilm(coleccion);
		selectRangeYear(coleccion);
		
		mongoClient.close();
		
		
	}
	
	private static void showInfoFilm(MongoCollection<Document> coleccion) {
		Scanner kB = new Scanner(System.in);
		
		System.out.println("¿Que pelicula quieres revisar su información?");
		int pos = Integer.parseInt(kB.nextLine())-1;
		//System.out.println(pos+"-"+titulos.get(pos));
		// CRUD operations

		MongoCursor<Document> cursor = coleccion.find(eq("titulo",titulos.get(pos))).iterator();
		
		
		JSONObject obj = new JSONObject(cursor.next().toJson());
		String titulo = obj.getString("titulo");
		String director = obj.getString("director");
		Double nota = obj.getDouble("nota");
		Integer anyo = obj.getInt("anyo");
		Integer presupuesto = obj.getInt("presupuesto");
		String base64 = obj.getString("img_base64");
		
		String mess = "=======================\n"+"DATOS PELICULA\n"+"=======================\n"+"Titulo: "+titulo+"\nDirector: "+director+"\nNota: "+nota+"\nAnyo: "+anyo+"\nPresupuesto: "+presupuesto+" millones de dolares";
		
		JOptionPane.showMessageDialog(null, mess, "Ficha peliculas",
				JOptionPane.INFORMATION_MESSAGE, generateImageIcon(base64));
		
	}
	
	private static void selectRangeYear(MongoCollection<Document> coleccion) {
		Scanner kB = new Scanner(System.in);
		
		System.out.println("Indicame el primer año");
		int anyo1 = Integer.parseInt(kB.nextLine());
		System.out.println("Indicame el segundo");
		int anyo2 = Integer.parseInt(kB.nextLine());
		// CRUD operations

		MongoCursor<Document> cursor = coleccion.find().iterator();
		int num = 1;
		while(cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			String titulo = obj.getString("titulo");
			Integer anyo = obj.getInt("anyo");
			
			
			if(anyo >= anyo1 && anyo <= anyo2) {
				System.out.println(num+".-"+titulo + " - "+anyo);
				num++;
			}
		}
		
		
	}
	
	private static ImageIcon generateImageIcon(String base64) {

		try {
			byte[] btDataFile = Base64.decodeBase64(base64);
			BufferedImage imgBF = ImageIO.read(new ByteArrayInputStream(btDataFile));
			Image img = imgBF.getScaledInstance(-1, 240, java.awt.Image.SCALE_SMOOTH);

			//ImageIO.write(imgBF, "jpg", new File("./imgs/" + name));

			ImageIcon imgIcon = new ImageIcon(img);

			return imgIcon;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
