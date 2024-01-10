package es.florida.model;

import static com.mongodb.client.model.Filters.*;
import org.bson.conversions.Bson;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
	
	public Model() {
		dC = new DataConnection();
		login = false;
		cards = new ArrayList();
	}

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
	        
	        return new DataConnection(ip,port,database,collections);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	private MongoDatabase connection() {
		
		dC = getDataConnection(new File("./connection.json"));
		
		mongoClient = new MongoClient(dC.getIp(),dC.getPort());
		MongoDatabase database = mongoClient.getDatabase(dC.getDatabase());
		
		return database;
	}
	
	private MongoCollection<Document> getCollectionUsers(MongoDatabase dB){
		MongoCollection<Document> collection = dB.getCollection(dC.getCollections()[2]);
		return collection;
	}
	private MongoCollection<Document> getCollectionImgs(MongoDatabase dB){
		MongoCollection<Document> collection = dB.getCollection(dC.getCollections()[0]);
		return collection;
	}
	
	private MongoCollection<Document> getCollectionRecords(MongoDatabase dB){
		MongoCollection<Document> collection = dB.getCollection(dC.getCollections()[1]);
		return collection;
	}
	
	public boolean login(View view) {
		
		
		try {
			String userInput = view.getTextFieldUser().getText();
			char[] passInputChar = view.getPasswordField().getPassword();
			char[] repeatPassInputChar = view.getRepeatPasswordField().getPassword();
			String passInput = new String(passInputChar);
			String repeatPassInput = new String(repeatPassInputChar);
			
			MongoDatabase connection = connection();
			MongoCollection<Document> userCollection = getCollectionUsers(connection);
			
			
			MongoCursor<Document> cursor = userCollection.find(eq("user",userInput)).iterator();
			
			JSONObject obj = new JSONObject(cursor.next().toJson());
			String userMDB = obj.getString("user");
			
			if(userMDB.equals(userInput) && passInput.equals(repeatPassInput)) {
				mongoClient.close();
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
			
			MongoCursor<Document> cursor = userCollection.find(eq("user",userInput)).iterator();
		
			
			
			if(!userExist(userInput)) {
				Document doc = new Document();
				
				if(passInput.equals(repeatPassInput) && !passInput.equals("") && !repeatPassInput.equals("")) {
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
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			mongoClient.close();
			success =false;
		}
		
		return success;
	}
	
	private boolean userExist(String userInput) {
		try {
			MongoDatabase connection = connection();
			MongoCollection<Document> userCollection = getCollectionUsers(connection);
			
			MongoCursor<Document> cursor = userCollection.find(eq("user",userInput)).iterator();
			
			JSONObject obj = new JSONObject(cursor.next().toJson());
			JOptionPane.showMessageDialog(null, "User "+obj.getString("user")+" exist", "Register error", JOptionPane.ERROR_MESSAGE);
			return true;
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
			
		}
	}
	
	public void generateCard(JButton[] btnsImgs, int countImg) {
		
		List<String> nameImg = new ArrayList();
		List<String> selected = new ArrayList();
		List<String> base64Selected = new ArrayList();
		List<Integer> posUsed = new ArrayList();
		
		MongoDatabase connection = connection();
		MongoCollection<Document> imgCollection = getCollectionImgs(connection);
		
		MongoCursor<Document> cursor = imgCollection.find().iterator();
		
		while(cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			nameImg.add(obj.getString("id"));
		}
		
		int index = 0;
		
		while(index != countImg) {
			Random random = new Random();
			int numRandom = random.nextInt(nameImg.size());
			
			if(!selected.contains(nameImg.get(numRandom))) {
				selected.add(nameImg.get(numRandom));
				selected.add(nameImg.get(numRandom));
				index++;
			}
		}
		
		for(String name : selected) {
			cursor = imgCollection.find(eq("id",name)).iterator();
			JSONObject obj = new JSONObject(cursor.next().toJson());
			base64Selected.add(obj.getString("base64"));
		}
		
		for(int i = 0; i < btnsImgs.length;) {
			Random random = new Random();
			int numRandom = random.nextInt(btnsImgs.length);
			if(!posUsed.contains(numRandom)) {
				cards.add(new Card(numRandom, selected.get(i),base64Selected.get(i),generateImageIcon(base64Selected.get(i),selected.get(i)),btnsImgs[numRandom]));
				i++;
			}
		}
		
	}
	
	private ImageIcon generateImageIcon(String base64, String name) {
		
		try {
			byte[] btDataFile = Base64.decodeBase64(base64);
			BufferedImage imgBF = ImageIO.read(new ByteArrayInputStream(btDataFile));
			Image img = imgBF.getScaledInstance(-1, 120, java.awt.Image.SCALE_SMOOTH);
			
			ImageIO.write(imgBF, "jpg", new File("./imgs/"+name));
			
			File fileImg = new File("./imgs/"+name);
			img = ImageIO.read(fileImg);
			ImageIcon imgIcon = new ImageIcon(img);
			
			return imgIcon;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	public String calculateSHA256(String input) throws NoSuchAlgorithmException {
		
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
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
