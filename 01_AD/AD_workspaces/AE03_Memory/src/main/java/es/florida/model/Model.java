package es.florida.model;

import static com.mongodb.client.model.Filters.*;
import org.bson.conversions.Bson;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.apache.commons.codec.binary.Base64;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import es.florida.view.View;

import org.bson.Document;
import org.json.JSONArray;

public class Model {

	private DataConnection dC;
	private MongoClient mongoClient;
	private List<Card> cards;
	private boolean login;
	private String currentUserLogued;
	private long initTime;
	private Record currentRecord;
	private List<Card> cardsTurned;
	private int countPairCards;

	public Model() {
		dC = new DataConnection();
		login = false;
		cards = new ArrayList();
		currentUserLogued = "";
		initTime = 0;
		currentRecord = new Record();
		cardsTurned = new ArrayList();
		countPairCards = 0;
	}

	/**
	 * Metodo para coger los datos de conexión a mondoDB y guardarlo en un objeto
	 * @param file Pasamos el archivo que queremos leer.
	 * @return retornamos el objeto con los datos de la conexión.
	 */
	public DataConnection getDataConnection(File file) {

		try {
			String jsonContent = new String(Files.readAllBytes(file.toPath()));
			JSONObject obj = new JSONObject(jsonContent);

			String ip = obj.getString("IP");
			int port = obj.getInt("port");
			String database = obj.getString("database");

			JSONArray collectionsJSON = obj.getJSONArray("collections");

			String[] collections = new String[collectionsJSON.length()];

			for (int i = 0; i < collectionsJSON.length(); i++) {
				collections[i] = collectionsJSON.getString(i);
			}

			return new DataConnection(ip, port, database, collections);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Metodo para conextarnos a la base de datos mongoDB
	 * @return retornamos la base de datos a la que nos hemos conectado.
	 */
	private MongoDatabase connection() {

		dC = getDataConnection(new File("./connection.json"));

		mongoClient = new MongoClient(dC.getIp(), dC.getPort());
		MongoDatabase database = mongoClient.getDatabase(dC.getDatabase());

		return database;
	}

	/**
	 * Metodo para coger una colección especifica en este caso usuarios.
	 * @param dB pasamos la base de datos donde conectamos para coger los datos.
	 * @return devolvemos la colección de Usuarios
	 */
	private MongoCollection<Document> getCollectionUsers(MongoDatabase dB) {
		MongoCollection<Document> collection = dB.getCollection(dC.getCollections()[2]);
		return collection;
	}

	/**
	 * Metodo para coger una colección especifica en este caso imgs.
	 * @param dB pasamos la base de datos donde conectamos para coger los datos.
	 * @return devolvemos la colección de Imagenes
	 */
	private MongoCollection<Document> getCollectionImgs(MongoDatabase dB) {
		MongoCollection<Document> collection = dB.getCollection(dC.getCollections()[0]);
		return collection;
	}

	/**
	 * Metodo para coger una colección especifica en este caso records.
	 * @param dB pasamos la base de datos donde conectamos para coger los datos.
	 * @return devolvemos la colección de Records
	 */
	private MongoCollection<Document> getCollectionRecords(MongoDatabase dB) {
		MongoCollection<Document> collection = dB.getCollection(dC.getCollections()[1]);
		return collection;
	}

	
	/**
	 * Metodo para hacer login que contrastamos los datos con la base de datos
	 * @param view pasamos el parametro para coger los datos del input.
	 * @return retornamos si el login es correcto o no con un booleano.
	 */
	public boolean login(View view) {

		try {
			String userInput = view.getTextFieldUser().getText();
			char[] passInputChar = view.getPasswordField().getPassword();
			char[] repeatPassInputChar = view.getRepeatPasswordField().getPassword();
			String passInput = new String(passInputChar);
			String repeatPassInput = new String(repeatPassInputChar);

			MongoDatabase connection = connection();
			MongoCollection<Document> userCollection = getCollectionUsers(connection);

			MongoCursor<Document> cursor = userCollection.find(eq("user", userInput)).iterator();

			JSONObject obj = new JSONObject(cursor.next().toJson());
			String userMDB = obj.getString("user");

			if (userMDB.equals(userInput) && passInput.equals(repeatPassInput)) {
				mongoClient.close();
				currentUserLogued = userMDB;
				return true;
			} else {
				mongoClient.close();
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			mongoClient.close();
			return false;
		}

	}

	/**
	 * Metodo para registrar un usuario en la base de datos.
	 * @param view pasamos el parametro para coger los datos del input.
	 * @return retornamos con un boolean si el registro ha sido correcto o no.
	 */
	public boolean register(View view) {

		boolean success = false;

		try {

			String userInput = view.getTextFieldUser().getText();
			char[] passInputChar = view.getPasswordField().getPassword();
			char[] repeatPassInputChar = view.getRepeatPasswordField().getPassword();
			String passInput = new String(passInputChar);
			String repeatPassInput = new String(repeatPassInputChar);

			MongoDatabase connection = connection();
			MongoCollection<Document> userCollection = getCollectionUsers(connection);

			MongoCursor<Document> cursor = userCollection.find(eq("user", userInput)).iterator();

			if (checkUserValid(userInput)) {
				if (!userExist(userInput)) {
					Document doc = new Document();

					if (passInput.equals(repeatPassInput) && !passInput.equals("") && !repeatPassInput.equals("")) {
						doc.append("user", userInput);
						doc.append("pass", calculateSHA256(passInput));
						userCollection.insertOne(doc);
						mongoClient.close();
						success = true;
					} else {
						mongoClient.close();
						success = false;
					}
				}
			}

		} catch (Exception e) {
			mongoClient.close();
			success = false;
		}

		return success;
	}

	/**
	 * Metodo que utilizamos para guardar los datos en la base de datos del record realizado, en caso de no haber realizado ningún record o ya haberlo guardado nos mostrara un mensaje de error.
	 */
	public void saveCurrentRecord() {
		MongoDatabase connection = connection();
		MongoCollection<Document> recordCollection = getCollectionRecords(connection);

		MongoCursor<Document> cursor = recordCollection.find().iterator();

		if(currentRecord.getUser() != null) {
			Document doc = new Document();
			doc.append("usuario", currentRecord.getUser());
			doc.append("dificultad", currentRecord.getDificult());
			doc.append("timestamp", currentRecord.getTimestamp());
			doc.append("duracion", currentRecord.getDuration());
			recordCollection.insertOne(doc);
			mongoClient.close();

			JOptionPane.showMessageDialog(null,
					"Record guardado.\nUsuario:  " + currentRecord.getUser() + "\nDificultad: "
							+ currentRecord.getDificult() + "\nTimestamp: " + currentRecord.getTimestamp() + "\nDuración: "
							+ currentRecord.getDuration(),
					"Saved", JOptionPane.INFORMATION_MESSAGE);
			
			currentRecord = new Record(null,0,null,0);
		} else {
			JOptionPane.showMessageDialog(null, "No hay ningún record guardado o ya se ha guardado anteriormente", "Not record",
					JOptionPane.ERROR_MESSAGE);
		}
		

	}

	/**
	 * Metodo donde comprobamos si el ususario existe
	 * @param userInput le pasamos el nombre de ususario que escribe en el input
	 * @return retornamos un booleano de si existe o no.
	 */
	private boolean userExist(String userInput) {

		try {
			MongoDatabase connection = connection();
			MongoCollection<Document> userCollection = getCollectionUsers(connection);

			MongoCursor<Document> cursor = userCollection.find(eq("user", userInput)).iterator();

			JSONObject obj = new JSONObject(cursor.next().toJson());
			JOptionPane.showMessageDialog(null, "User " + obj.getString("user") + " exist", "Register error",
					JOptionPane.ERROR_MESSAGE);
			return true;

		} catch (Exception e) {
			return false;

		}
	}

	/**
	 * Metodo para generar las cartas que vamos a utilizar.
	 * @param btnsImgs Pasamos el array de JButons para poder asignarlos.
	 * @param countImg Pasamos la cantidad de imagenes que se van a utilizar (Difucultad 8 - 4 imagenes) (Dificultad 16 - 8) imagenes
	 */
	public void generateCard(JButton[] btnsImgs, int countImg) {

		List<String> nameImg = new ArrayList();
		List<String> selected = new ArrayList();
		List<String> base64Selected = new ArrayList();
		List<Integer> posUsed = new ArrayList();

		MongoDatabase connection = connection();
		MongoCollection<Document> imgCollection = getCollectionImgs(connection);

		MongoCursor<Document> cursor = imgCollection.find().iterator();

		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			nameImg.add(obj.getString("id"));
		}

		int index = 0;

		while (index != countImg) {
			Random random = new Random();
			int numRandom = random.nextInt(nameImg.size());

			if (!selected.contains(nameImg.get(numRandom))) {
				selected.add(nameImg.get(numRandom));
				selected.add(nameImg.get(numRandom));
				index++;
			}
		}

		for (String name : selected) {
			cursor = imgCollection.find(eq("id", name)).iterator();
			JSONObject obj = new JSONObject(cursor.next().toJson());
			base64Selected.add(obj.getString("base64"));
		}

		for (int i = 0; i < btnsImgs.length;) {
			Random random = new Random();
			int numRandom = random.nextInt(btnsImgs.length);
			if (!posUsed.contains(numRandom)) {
				posUsed.add(numRandom);
				cards.add(new Card(numRandom, selected.get(i), base64Selected.get(i),
						generateImageIcon(base64Selected.get(i), selected.get(i)), btnsImgs[numRandom], false, true));
				i++;
			}
		}

	}

	/**
	 * Metodo que utilizamos para generar la imagen.
	 * @param base64 pasamos el base64 de la base de datos para generar la imagen.
	 * @param name pasamos el nombre que va tener la imagen.
	 * @return
	 */
	private ImageIcon generateImageIcon(String base64, String name) {

		try {
			byte[] btDataFile = Base64.decodeBase64(base64);
			BufferedImage imgBF = ImageIO.read(new ByteArrayInputStream(btDataFile));
			Image img = imgBF.getScaledInstance(-1, 120, java.awt.Image.SCALE_SMOOTH);

			ImageIO.write(imgBF, "jpg", new File("./imgs/" + name));

			while (!new File("./imgs/" + name).exists()) {

			}

			File fileImg = new File("./imgs/" + name);
			img = ImageIO.read(fileImg);
			ImageIcon imgIcon = new ImageIcon(img);

			return imgIcon;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Metodo que utilizamos para comprobar si la duración que hemos realizado ha sido la mejor.
	 * @param dificult pasamos la dificultad en la que estamos jugando
	 * @param duration y la duración que hemos realizado.
	 * @return
	 */
	public boolean isBestDuration(int dificult, int duration) {

		List<Record> records = new ArrayList();
		MongoDatabase connection = connection();
		MongoCollection<Document> recordCollection = getCollectionRecords(connection);

		boolean isBest = false;

		MongoCursor<Document> cursor = recordCollection.find(eq("dificultad", dificult)).iterator();
		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			records.add(new Record(obj.getString("usuario"), obj.getInt("dificultad"), obj.getString("timestamp"),
					obj.getInt("duracion")));
		}
		Collections.sort(records);

		for (Record record : records) {
			System.out.println(record.getDuration());
		}

		if (records.get(0).getDuration() > duration) {
			isBest = true;
		}

		return isBest;
	}

	/**
	 * Metodo que utilizamos para para mostrar los records que hay en la base de datos.
	 * @param view le pasamos la vista para poder insertar el texto.
	 */
	public void showRecords(View view) {

		List<Record> records24 = new ArrayList();
		List<Record> records44 = new ArrayList();

		MongoDatabase connection = connection();
		MongoCollection<Document> recordCollection = getCollectionRecords(connection);

		MongoCursor<Document> cursor = recordCollection.find().iterator();

		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());

			if (obj.getInt("dificultad") == 8) {
				records24.add(new Record(obj.getString("usuario"), obj.getInt("dificultad"), obj.getString("timestamp"),
						obj.getInt("duracion")));
			}

			if (obj.getInt("dificultad") == 16) {
				records44.add(new Record(obj.getString("usuario"), obj.getInt("dificultad"), obj.getString("timestamp"),
						obj.getInt("duracion")));
			}
		}

		Collections.sort(records24);
		Collections.sort(records44);

		String text24 = "";
		String text44 = "";

		for (Record record : records24) {
			text24 += record.getUser() + " >>> " + record.getDuration() + " seconds (" + record.getTimestamp() + ")\n";
		}

		for (Record record : records44) {
			text44 += record.getUser() + " >>> " + record.getDuration() + " seconds (" + record.getTimestamp() + ")\n";
		}

		view.getTextPane24().setText(text24);
		view.getTextPane44().setText(text44);

	}

	/**
	 * Metodo que utilizamos para ver el tiempo que ha estado en el juego
	 * @param init pasamos cuando se ha iniciado el juego
	 * @param end pasamos cuando ha finalizado.
	 * @return retornamos los segundos que hemos estado.
	 */
	public double generateTotalTime(long init, long end) {

		long totalTime = end - init;

		double seconds = totalTime / 1000.0;

		return seconds;
	}

	/**
	 * Metodo que utilizamos para generar el timestamp
	 * @return retornamos un string con el timestamp
	 */
	public String generateTimeStamp() {

		Date actualDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String timestamp = format.format(actualDate);

		return timestamp;

	}

	/**
	 * Eliminamos los ficheros que se encuentran dentro de la carpeta imgs.
	 */
	public void deleteFiles() {
		File directory = new File("./imgs");

		String[] files = directory.list();

		for (String file : files) {
			if (new File("./imgs/" + file).exists()) {
				new File("./imgs/" + file).delete();
			}

		}
	}

	/**
	 * Metodo que se utiliza para comparar las cartas que se han dado la vuelta.
	 * @return retornamos si son iguales o no.
	 */
	public boolean compareCards() {
		if (!cardsTurned.get(0).getName().equals(cardsTurned.get(1).getName())) {
			JOptionPane.showMessageDialog(null, "Fail", "Try Again", JOptionPane.INFORMATION_MESSAGE);
			cardsTurned.get(0).turnOffCard();
			cardsTurned.get(1).turnOffCard();
			return false;
		} else {
			cardsTurned.get(0).setClikable(false);
			cardsTurned.get(1).setClikable(false);
			return true;
		}
	}

	/**
	 * Metodo que utilizamos para comprobar que el usuario no tenga ningún caracter estaño, para el registro.
	 * @param userField pasamos el usuario insertado
	 * @return si no dispone de ningún carcter estraño devolvemos falso.
	 */
	private boolean checkChar(String userField) {

		int[][] posChars = new int[][] { { 32, 47 }, { 58, 64 }, { 91, 94 }, { 96, 96 }, { 123, 255 } };
		boolean check = false;

		if (!userField.contains(" ")) { // Comprobamos primero que no tenga un espacio en blanco.
			for (int i = 0; i < posChars.length; i++) {
				for (int j = posChars[i][0]; j <= posChars[i][1]; j++) {
					char c = (char) j;
					if (userField.contains(String.valueOf(c))) {
						check = true;
					}
				}
			}
		}

		return check;
	}

	/**
	 * Metodo que utilizamos para comprobar que el nombre de usuario en el registro es valido
	 * @param userField le pasamos lo que hay en el input
	 * @return si es valido devolvemos true
	 */
	private boolean checkUserValid(String userField) {
		boolean check = false;
		if (!userField.equals("")) {
			if (!checkChar(userField)) {
				check = true;
			}
		}
		return check;
	}

	/**
	 * Metodo donde pasamos la contraseña a SHA-256
	 * @param input le pasamos la contraseña
	 * @return devolvemos la contraseña haseada para guardarla en mongoDB
	 * @throws NoSuchAlgorithmException
	 */
	public String calculateSHA256(String input) throws NoSuchAlgorithmException {

		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
		StringBuilder hexString = new StringBuilder();
		for (byte b : hash) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}

		return hexString.toString();
	}

	/**
	 * @param cardsTurned the cardsTurned to set
	 */
	public void setCardsTurned(List<Card> cardsTurned) {
		this.cardsTurned = cardsTurned;
	}

	/**
	 * @return the countPairCards
	 */
	public int getCountPairCards() {
		return countPairCards;
	}

	/**
	 * @param countPairCards the countPairCards to set
	 */
	public void setCountPairCards(int countPairCards) {
		this.countPairCards = countPairCards;
	}

	/**
	 * @return the cardsTurned
	 */
	public List<Card> getCardsTurned() {
		return cardsTurned;
	}

	/**
	 * @return the currentRecord
	 */
	public Record getCurrentRecord() {
		return currentRecord;
	}

	/**
	 * @param currentRecord the currentRecord to set
	 */
	public void setCurrentRecord(Record currentRecord) {
		this.currentRecord = currentRecord;
	}

	/**
	 * @param initTime the initTime to set
	 */
	public void setInitTime(long initTime) {
		this.initTime = initTime;
	}

	/**
	 * @return the currentUserLogued
	 */
	public String getCurrentUserLogued() {
		return currentUserLogued;
	}

	/**
	 * @return the initTime
	 */
	public long getInitTime() {
		return initTime;
	}

	/**
	 * @return the cards
	 */
	public List<Card> getCards() {
		return cards;
	}

	/**
	 * @return the login
	 */
	public boolean isLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(boolean login) {
		this.login = login;
	}

}
