package es.florida.ej_t4;

import java.io.*;
import java.net.*;

public class ClienteCalculo {
	public static void main(String[] args) throws IOException {
		System.out.println("CLIENTE >>> Arranca cliente");
		System.out.println("CLIENTE >>> Conexion al servidor");
		InetSocketAddress direccion = new InetSocketAddress("localhost", 9876);
		Socket socket = new Socket();
		socket.connect(direccion);
		System.out.println("CLIENTE >>> Preparado canal para recibir resultado");
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader bfr = new BufferedReader(isr);
		System.out.println("CLIENTE >>> Envio de datos para el calculo");
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		pw.print("+\n");
		pw.print("100\n");
		pw.print("50\n");
		pw.flush();
		String resultado = bfr.readLine();
		System.out.println("CLIENTE >>> Recibe resultado: " + resultado);

	}
}
