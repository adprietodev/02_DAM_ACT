package es.florida.MP3T1;

import java.io.*;
import org.w3c.dom.*;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.*;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Model {

	private List<Libro> libros;
	
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
			
			for(int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					int id = Integer.parseInt(eElement.getAttribute("id"));
					String title = eElement.getElementsByTagName("titulo").item(0).getTextContent();
					String year = eElement.getElementsByTagName("anyoPublicacion").item(0).getTextContent();
					String autor = eElement.getElementsByTagName("autor").item(0).getTextContent();
					String publisher = eElement.getElementsByTagName("editorial").item(0).getTextContent();
					int numPages = Integer.parseInt(eElement.getElementsByTagName("numPaginas").item(0).getTextContent());
					
					this.libros.add(new Libro(id,title,autor,year,publisher,numPages));
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
			
			for(Libro lib : libros) {
				
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
			
			TransformerFactory tranFactory = TransformerFactory.newInstance(); //Crea serialización
			Transformer aTransformer = tranFactory.newTransformer();
			aTransformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			aTransformer.setOutputProperty("{http:/xml.apache.org/xslt}indet-amount", "4");
			aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			
			FileWriter fW = new FileWriter("libros.xml");
			StreamResult result = new StreamResult(fW);
			aTransformer.transform(source, result);
			fW.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
