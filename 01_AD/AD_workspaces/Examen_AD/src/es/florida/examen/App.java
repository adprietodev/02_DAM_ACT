package es.florida.examen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class App {
	
	DataCON dataCon = new DataCON();
	List<ContDB> contDB = new ArrayList<ContDB>();
	
	public class XMLData {
		private String url;
		private String user;
		private String password;
		
		public XMLData (String url, String user, String password) {
			this.url = url;
			this.user = user;
			this.password = password;
		}
		
	}
	
	public class DataCON {
		
		private String url;
		private String user;
		private String password;
		
		public DataCON() {
			
		}
		
		public DataCON (String url, String user, String password) {
			this.url = url;
			this.user = user;
			this.password = password;
		}
	}
	
	public static class DataTable {
		private String destino;
		private String continente;
		private int precio;
		
		public DataTable(String destino, String continente, int precio) {
			this.destino = destino;
			this.continente = continente;
			this.precio = precio;
		}
	}
	
	public class ContDB {
		private String id;
		private String destino;
		private String continente;
		private String precio;
		
		public ContDB() {
			
		}
		
		public ContDB(String id, String destino, String continente, String precio) {
			this.id = id;
			this.destino = destino;
			this.continente = continente;
			this.precio = precio;
		}
		
	}
	
	public static void main(String[] args) {
		
		App app = new App();
		
		File fileCon = new File("conexion.txt");
		
		XMLData xmlData = app.readFileCon(fileCon);
		
		List<DataTable> dataTable = new ArrayList<DataTable>();
		
		dataTable.add(new DataTable("Madrid", "Europa", 100));
		dataTable.add(new DataTable("Londres", "Europa", 150));
		dataTable.add(new DataTable("Paris", "Europa", 135));
		dataTable.add(new DataTable("Roma", "Europa", 105));
		dataTable.add(new DataTable("Moscu", "Europa", 545));
		dataTable.add(new DataTable("Nova York", "America", 725));
		dataTable.add(new DataTable("Buenos Aires", "America", 889));
		dataTable.add(new DataTable("Kuala Lumpur", "Asia", 1095));
		dataTable.add(new DataTable("Tokio", "Asia", 1200));
		dataTable.add(new DataTable("Sidney", "Oceania", 1350));
		
		app.writeXmlFile(xmlData);
		
		app.dataCon = app.readXML(new File("config.xml"));
		
//		try {
//			System.out.println(app.connectDB(app.dataCon).isValid(0));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//Insertar datos en tabla
		//app.queryInsert(dataTable);
		
		//app.consultaContinentes();;
		
		app.querySelect();
		app.writeXmlFileDB(app.contDB);
		
		try {
			app.connectDB(app.dataCon).close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//SELECT continente, MAX(precio) AS Precio_Maximo, AVG(precio) AS Media_precios FROM vuelos GROUP BY continente
		//SELECT continente, MIM(precio) AS Precio_Minimo, AVG(precio) AS Media_precios FROM vuelos GROUP BY continente

	}
	
	public Connection connectDB(DataCON dataCon) {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Si dejara acceder con examen examen seria la linea comentada
			//con = DriverManager.getConnection(dataCon.url, dataCon.user, dataCon.password);
			con = DriverManager.getConnection(dataCon.url, "root", "");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	private void queryInsert(List<DataTable> dataTable) {

		try {

			PreparedStatement psInsertar = null;
			
			for(DataTable data : dataTable) {
				psInsertar  = connectDB(dataCon).prepareStatement("INSERT INTO vuelos (id,destino,continente,precio) VALUES (NULL,?,?,?)");

				psInsertar.setString(1,data.destino);
				psInsertar.setString(2,data.continente);
				psInsertar.setInt(3,data.precio);
				int resultadoInsertar = psInsertar.executeUpdate();
			}
			

			psInsertar.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}
	}
	
	private void consultaContinentes() {
		String[] conExist = {"Europa","America","Asia","Africa","Oceania"};
		String currentCont = "";
		try {
			Connection con = connectDB(dataCon);
			Statement stmt = con.createStatement();

			//SELECT continente, MAX(precio) AS Precio_Maximo, AVG(precio) AS Media_precios FROM vuelos GROUP BY continente
			//SELECT continente, MIM(precio) AS Precio_Minimo, AVG(precio) AS Media_precios FROM vuelos GROUP BY continente
			ResultSet rs = null;
			for(String continente : conExist) {
				currentCont = continente;
				System.out.println("SELECT continente, MAX(precio) AS Precio_Maximo, AVG(precio) AS Media_precios FROM vuelos WHERE continente='"+continente+"' GROUP BY continente");
				
				rs = stmt.executeQuery("SELECT continente, MAX(precio), AVG(precio) FROM vuelos WHERE continente='"+continente+"' GROUP BY continente");

				System.out.println(rs.getString(1));
				

				rs.close();
				stmt.close();
			}
			

		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
			System.out.println(currentCont+": Sin datos");
		}
	}
	
	
	private void querySelect() {
		try {
			Connection con = connectDB(dataCon);
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM vuelos");

			
			while (rs.next()) {
				contDB.add(new ContDB(String.valueOf(rs.getInt(1)),rs.getString(2),rs.getString(3),String.valueOf(rs.getInt(4))));
			}

			rs.close();
			stmt.close();
			
//			for(ContDB data : contDB) {
//				System.out.println(data.id);
//			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void writeXmlFileDB(List<ContDB> contDB) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();

			Element raiz = doc.createElement("destinos");

			doc.appendChild(raiz);

			for (ContDB data : contDB) {

				Element destino = doc.createElement("destino");
				String id = String.valueOf(data.id);
				destino.setAttribute("id", id);
				raiz.appendChild(destino);

				Element ciudad = doc.createElement("ciudad");
				ciudad.appendChild(doc.createTextNode(data.destino));
				destino.appendChild(ciudad);

				Element continente = doc.createElement("continente");
				continente.appendChild(doc.createTextNode(data.continente));
				destino.appendChild(continente);

				Element precio = doc.createElement("precio");
				precio.appendChild(doc.createTextNode(data.precio));
				destino.appendChild(precio);

			}

			TransformerFactory tranFactory = TransformerFactory.newInstance(); // Crea serialización
			Transformer aTransformer = tranFactory.newTransformer();
			aTransformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			aTransformer.setOutputProperty("{http:/xml.apache.org/xslt}indet-amount", "4");
			aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);

			FileWriter fW = new FileWriter("datos-db.xml");
			StreamResult result = new StreamResult(fW);
			aTransformer.transform(source, result);
			fW.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DataCON readXML(File file) {

		DataCON dataCon = new DataCON();
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);

			Element raiz = (Element) doc.getDocumentElement();

			NodeList nodeList = doc.getElementsByTagName("config1");

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					String url = eElement.getElementsByTagName("url").item(0).getTextContent();
					String user = eElement.getElementsByTagName("user").item(0).getTextContent();
					String password = eElement.getElementsByTagName("password").item(0).getTextContent();
					dataCon = new DataCON(url,user,password);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataCon;
	}
	
	public XMLData readFileCon(File file) {
		
		String conector = "";
		String ip = "";
		String port = "";
		String bdd = "";
		String url = "";
		String user = "";
		String password = "";
		
		try {
			FileReader fR = new FileReader(file);
			BufferedReader bR = new BufferedReader(fR);
			
			String linea = bR.readLine();
			
			while(linea !=  null) {
				String[] splitLn = linea.split("=");
				if(splitLn[0].equals("conector")) {
					conector = splitLn[1];
				}
				if(splitLn[0].equals("ip")) {
					ip = splitLn[1];
				}
				if( splitLn[0].equals("port")) {
					port =  splitLn[1];
				}
				if(splitLn[0].equals("bdd")) {
					bdd = splitLn[1];
				}
				if(splitLn[0].equals("user")) {
					user = splitLn[1];
				}
				if(splitLn[0].equals("password")) {
					password = splitLn[1];
				}
				linea = bR.readLine();
			}
			
			bR.close();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		url = conector+"://"+ip+":"+port+"/"+bdd;
		
		XMLData xmlData = new XMLData(url,user,password);
		return xmlData;
		
	}
	
	public void writeXmlFile(XMLData xmlData) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();

			Element raiz = doc.createElement("configurations");

			doc.appendChild(raiz);
			
			Element config = doc.createElement("config1");
			raiz.appendChild(config);

			Element url = doc.createElement("url");
			url.appendChild(doc.createTextNode(xmlData.url));
			config.appendChild(url);

			Element user = doc.createElement("user");
			user.appendChild(doc.createTextNode(xmlData.user));
			config.appendChild(user);

			Element password = doc.createElement("password");
			password.appendChild(doc.createTextNode(xmlData.password));
			config.appendChild(password);


			TransformerFactory tranFactory = TransformerFactory.newInstance(); // Crea serialización
			Transformer aTransformer = tranFactory.newTransformer();
			aTransformer.setOutputProperty(OutputKeys.VERSION, "1.0");
			aTransformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			aTransformer.setOutputProperty("{http:/xml.apache.org/xslt}indet-amount", "4");
			aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);

			FileWriter fW = new FileWriter("config.xml");
			StreamResult result = new StreamResult(fW);
			aTransformer.transform(source, result);
			fW.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
