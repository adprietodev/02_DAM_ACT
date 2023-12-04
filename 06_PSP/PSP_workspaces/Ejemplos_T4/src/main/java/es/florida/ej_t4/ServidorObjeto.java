package es.florida.ej_t4;

import java.io.*;
import java.net.*;

public class ServidorObjeto {

	public static void main(String[] args) {
		
		try {
			int numeroPuerto = 5000;
			ServerSocket servidor = new ServerSocket(numeroPuerto);
			System.err.println("SERVIDOR >> Escuchando...");
			
			Socket cliente = servidor.accept();
			ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
			
			Persona p = new Persona("Nombre",0);
			outObjeto.writeObject(p);
			System.err.println("SERVIDOR >> Envio a cliente: " + p.getNombre() + " - " + p.getEdad());
			
			ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
			
			Persona pMod = (Persona) inObjeto.readObject();
			
			System.err.println("SERVIDOR >> Recibo de cliente: " + pMod.getNombre()+ " - " +
			pMod.getEdad());
			
			outObjeto.close();
			inObjeto.close ();
			cliente.close ();
			servidor.close(); 
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		

	}

}
