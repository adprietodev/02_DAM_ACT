package es.florida.ej_t4;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.*;

public class Cliente {

	public static void main(String[] args) {
		String host = "localhost";
		int puertoDestino = 1234;
		Socket socket = new Socket();
		InetSocketAddress direcction = new InetSocketAddress(host,puertoDestino);
		
		Libro libro = new Libro("One Piece nº01","Echiro Oda");

		try {
			socket.connect(direcction);
			
			//Ejercicio 4
//			System.out.println("CLIENTE >>> Preparado canal para recibir respuesta");
//			InputStream is = socket.getInputStream();
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader bfr = new BufferedReader(isr);
			
			//Ejercicio 3
			
//			System.out.println("CLIENTE >>> Envio de datos");
//			PrintWriter pw = new PrintWriter(socket.getOutputStream());
//			pw.println("Hola Mundo"); //Para que funcione bien recordad poner salto de linea
//			pw.flush();
			
			
			
			//Ejercicio 5 - Objetos
			ObjectOutputStream lMod = new ObjectOutputStream(socket.getOutputStream());
			lMod.writeObject(libro);
			System.out.println("CLIENTE >> Envio al servidor: "+libro.getTitulo() + " - " +libro.getAutor()); 
			
			//Ejercicio 6
			System.out.println("CLIENTE >>> Preparado canal para recibir respuesta - OBJETO");
			ObjectInputStream inObject = new ObjectInputStream(socket.getInputStream());
			Libro l = (Libro) inObject.readObject();
			System.out.println("CLIENTE >> Recibido del servidor: "+l.getTitulo() + " - " +l.getAutor()); 
			
//			String mensaje = bfr.readLine();
//			
//			System.out.println("Respuesta del servidor: "+mensaje);
			
			inObject.close();
			lMod.close();
			socket.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
