package es.florida.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.sun.net.httpserver.*;

public class ServerHTTP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try {
			String host = "localhost"; //127.0.0.1
			int puerto = 5000;
			InetSocketAddress directionTCPIP = new InetSocketAddress(host, puerto);
			int backlog = 0;
			HttpServer servidor = HttpServer.create(directionTCPIP, backlog);
			
			GestorHTTP gestorHTTP = new GestorHTTP();
			String rutaRespuesta = "/prueba";
			servidor.createContext(rutaRespuesta, gestorHTTP);
			
			//Opción 1 de ejecución: no multihilo
			//servidor.setExecute(null);
			
			//Opción 2 de ejecución: multiHilo con ThreadPoolExcutor
			ThreadPoolExecutor thPoolEx = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
			servidor.setExecutor(thPoolEx);
			servidor.start();
			
			System.out.println("Servidor HTTP arranca en el puerto: "+puerto);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
