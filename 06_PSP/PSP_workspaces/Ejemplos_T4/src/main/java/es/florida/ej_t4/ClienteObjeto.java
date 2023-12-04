package es.florida.ej_t4;

import java.io.*;
import java.net.*;

public class ClienteObjeto {
	public static void main(String[] arg) throws UnknownHostException, IOException, ClassNotFoundException {
		
		String host = "localhost";
		int puerto = 5000;
		System.out.println("CLIENTE >> Arranca cliente");
		
		Socket cliente = new Socket(host, puerto);
		ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
		
		Persona p = (Persona) inObjeto.readObject();
		System.out.println("CLIENTE >> Recibo del servidor: " + p.getNombre() + " - " + p.getEdad());
		p.setNombre("Jose Garcia");
		p.setEdad(30);
		
		ObjectOutputStream pMod = new ObjectOutputStream(cliente.getOutputStream());
		pMod.writeObject(p);
		System.out.println("CLIENTE >> Envio al servidor: " + p.getNombre() + " - " + p.getEdad());
		inObjeto.close();
		pMod.close();
		cliente.close();
	}
}