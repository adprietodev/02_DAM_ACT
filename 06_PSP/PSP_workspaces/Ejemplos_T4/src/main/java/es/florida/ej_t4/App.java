package es.florida.ej_t4;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		App app = new App();
		app.conector();
	}
	
	//Ejemplo de cliente
	public void conector() {
		String destino = "www.google.com";
		int puertoDestino = 80;
		Socket socket = new Socket();
		InetSocketAddress direccion =  new InetSocketAddress(destino, puertoDestino);
		
		try {
			socket.connect(direccion);
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			System.out.println("Conexión realizada");
			
			//Envio/recepción de objetos
			//Los streams soportan el envío/recepción de bytes, tipos primitivos, caracteres y objetos.
			ObjectOutputStream outObjeto = new ObjectOutputStream(os);
			ObjectInputStream inObjeto = new ObjectInputStream(is);
			//Las clases de los objetos que se desee enviar/recibir a través de la conexión deben de implementar interfaz Serializable
			
		}catch(IOException e) {
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}
	}
	
	//Transmisión y recepción de información
	//Envío/recepción de objetos
	
	

}
