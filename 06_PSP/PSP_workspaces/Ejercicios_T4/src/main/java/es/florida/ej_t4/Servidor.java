package es.florida.ej_t4;

import java.io.*;
import java.net.*;

public class Servidor {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		System.err.println("SERVIDOR >>> Arranca el servidor, espera peticion");
		ServerSocket server = null;
		try {
			server = new ServerSocket(1234);
		} catch (IOException e) {
			System.err.println("SERVIDOR >>> Error");
			return;
		}

		System.err.println("SERVIDOR >> Escuchando...");
		Socket cliente = server.accept();
		
//		InputStream iS = cliente.getInputStream();
//		InputStreamReader iSR = new InputStreamReader(iS);
//		BufferedReader bR = new BufferedReader(iSR);
//
//		System.err.println("SERVIDOR >>> Lee datos para la operacion");
//		String mensaje = bR.readLine();
//
//		System.err.println("Mensaje recibido por el cliente: " + mensaje);

		//Ejercicio 4
//		System.err.println("SERVIDOR >> Enviando...");
//		OutputStream os = cliente.getOutputStream();
//		PrintWriter pw = new PrintWriter(os);
//		pw.write("Recibido");
//		pw.flush();
		
		//Ejercicio 6
//		ObjectOutputStream outObject = new ObjectOutputStream(cliente.getOutputStream());
//		Libro l =  new Libro("Elantris","Brandon Sanderson");
//		outObject.writeObject(l);
//		System.err.println("SERVIDOR >> Envio a cliente: " + l.getTitulo() + " - " + l.getAutor());
		
		//Ejercicio 7
		
		
		//Ejercicio 5
		ObjectInputStream inObject =  new ObjectInputStream(cliente.getInputStream());
		Libro lMod = (Libro) inObject.readObject();
		System.err.println("SERVIDOR >> Recibo de cliente: " + lMod.getTitulo()+ " - " +lMod.getAutor());
		
		//Ejercicio 7
		
		lMod.setTitulo("Nacidos de la bruma");
		lMod.setAutor("Brandon Sanderson");
		
		ObjectOutputStream outObject = new ObjectOutputStream(cliente.getOutputStream());
		outObject.writeObject(lMod);
		System.err.println("SERVIDOR >> Envio a cliente (Objeto modificado): " + lMod.getTitulo() + " - " + lMod.getAutor());
		
		//outObject.close();
		inObject.close();
		cliente.close();
		server.close();
		
		System.err.println("SERVIDOR >> Enviado");

	}

}
