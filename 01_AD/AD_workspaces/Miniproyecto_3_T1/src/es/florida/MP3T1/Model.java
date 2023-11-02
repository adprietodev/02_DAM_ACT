package es.florida.MP3T1;

import java.io.*;
import org.w3c.dom.*;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.*;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Model {

	private List<Libro> libros;
	private Libro bookExist;

	public Model() {
		this.libros = new ArrayList<Libro>();
	}

	public void generateListAtXML(File file) {

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);

			Element raiz = (Element) doc.getDocumentElement();

			NodeList nodeList = doc.getElementsByTagName("libro");

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					int id = Integer.parseInt(eElement.getAttribute("id"));
					String title = eElement.getElementsByTagName("titulo").item(0).getTextContent();
					String year = eElement.getElementsByTagName("anyoPublicacion").item(0).getTextContent();
					String autor = eElement.getElementsByTagName("autor").item(0).getTextContent();
					String publisher = eElement.getElementsByTagName("editorial").item(0).getTextContent();
					int numPages = Integer.parseInt(eElement.getElementsByTagName("numPaginas").item(0).getTextContent());

					this.libros.add(new Libro(id, title, autor, year, publisher, numPages));
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeXmlFile(List<Libro> libros) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();

			Element raiz = doc.createElement("libros");

			doc.appendChild(raiz);

			for (Libro lib : libros) {

				Element libro = doc.createElement("libro");
				String id = String.valueOf(lib.getId());
				libro.setAttribute("id", id);
				raiz.appendChild(libro);

				Element titulo = doc.createElement("titulo");
				titulo.appendChild(doc.createTextNode(lib.getTitle()));
				libro.appendChild(titulo);

				Element autor = doc.createElement("autor");
				autor.appendChild(doc.createTextNode(lib.getAutor()));
				libro.appendChild(autor);

				Element anyo = doc.createElement("anyoPublicacion");
				anyo.appendChild(doc.createTextNode(lib.getYearPub()));
				libro.appendChild(anyo);

				Element publiser = doc.createElement("editorial");
				publiser.appendChild(doc.createTextNode(lib.getPublisher()));
				libro.appendChild(publiser);

				Element numP = doc.createElement("numPaginas");
				numP.appendChild(doc.createTextNode(String.valueOf(lib.getNumPages())));
				libro.appendChild(numP);

			}

			TransformerFactory tranFactory = TransformerFactory.newInstance(); // Crea serialización
			Transformer aTransformer = tranFactory.newTransformer();
			aTransformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			aTransformer.setOutputProperty("{http:/xml.apache.org/xslt}indet-amount", "4");
			aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);

			FileWriter fW = new FileWriter("libros.xml");
			StreamResult result = new StreamResult(fW);
			aTransformer.transform(source, result);
			fW.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Libro createBook(String title, String autor, String publisher, String yearP, int numPages) {
		int lastId = libros.get(libros.size() - 1).getId();

		libros.add(new Libro((lastId + 1), title, autor, yearP, publisher, numPages));

		return new Libro((lastId + 1), title, autor, yearP, publisher, numPages);

	}

	public boolean checkBookExist(String title) {
		for (Libro lib : libros) {
			if (lib.getTitle().equals(title)) {
				showDialog("El libro ya existe, por favor inserta otro libro", "Libro ya existe",
						JOptionPane.ERROR_MESSAGE);
				return true;
			}
			break;
		}
		return false;
	}

	public boolean checkIntField(String num) {
		String[] numSplit = num.split("[,.\\-:;!¡?¿ ]");
		if (numSplit.length == 1) {
			double numero;

			try {
				numero = Double.parseDouble(numSplit[0]);
				return Double.isNaN(numero);
			} catch (NumberFormatException e) {
				return true;
			}
		} else {
			return true;
		}

	}

	public String selectBook() {

		String[] options = new String[libros.size()];

		for (int i = 0; i < libros.size(); i++) {
			options[i] = String.valueOf(libros.get(i).getId());
		}

		JComboBox<String> comboBox = new JComboBox<>(options);

		int choice = JOptionPane.showConfirmDialog(null, comboBox, "Selecciona una opción",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		
		
		if (choice == JOptionPane.OK_OPTION) {
			String selectedOption = (String) comboBox.getSelectedItem();
			if (selectedOption != null) {
				return selectedOption;
			} else {
				
				return "No has saleccionado ninguna opción";
			}
		} else {
			return "cancelado";
		}
	}
	
	public void deleteBook(int id) {
		for(Libro book : libros) {
			if(book.getId() == id) {
				libros.remove(book);
				showDialog("Libro "+book.getTitle()+" con ID: "+book.getId()+", se ha eliminado correctamente","Libro eliminado",JOptionPane.INFORMATION_MESSAGE);
				break;
			}
		}
	}

	public void updateBook(Libro book) {
		for(int i= 0; i < libros.size();i++) {
			if(libros.get(i).getId() == book.getId()) {
				libros.set(i, book);
				break;
			}
		}
	}
	
	public Libro showInfoBookSelected(int id) {
		for(Libro book : libros) {
			System.out.println(book.getId());
			if(book.getId() == id) {
				return book;
			}
			
		}
		return new Libro(0,"","","","",0);
	}
	
	public String checkStringsField(String title, String autor, String publisher, String yearP, String numPages) {

		String[] strs = { title, autor, publisher };

		if (!checkIntField(numPages) && !checkIntField(yearP)) {
			String mess = "valid";
			for (String str : strs) {
				if (str.equals("") || str.equals(" ")) {
					mess = "Algún campo esta vacío, por favor rellena todos los campos.";
				}
			}
			return mess;
		} else {
			return "Numero de paginas o fecha no valido, recuerda deben ser numeros enteros";
		}

	}

	public void showDialog(String mess, String titleMess, int num) {
		JOptionPane.showMessageDialog(null, mess, titleMess, num);
	}

	public List<Libro> getListBooks() {
		return libros;
	}
}
