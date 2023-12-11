package es.florida.ej_t4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorCalculo {
	
	public static int extraerNumero(String linea) {
		int numero;
		try {
			numero = Integer.parseInt(linea);
		} catch (NumberFormatException e) {
			numero = 0;
		}
		if (numero >= 100000000) {
			numero = 0;
		}
		return numero;
	}

	public static int calcular(String op, String n1, String n2) {
		int resultado = 0;
		char simbolo = op.charAt(0);
		int num1 = extraerNumero(n1);
		int num2 = extraerNumero(n2);
		if (simbolo == '+') {
			resultado = num1 + num2;
		}
		if (simbolo == '-') {
			resultado = num1 - num2;
		}
		if (simbolo == 'x') {
			resultado = num1 * num2;
		}
		if (simbolo == '/') {
			resultado = num1 / num2;
		}
		return resultado;
	}

	public static void main(String[] args) throws IOException {

		System.err.println("SERVIDOR >>> Arranca el servidor, espera peticion");
		ServerSocket socketEscucha = null;
		try {
			socketEscucha = new ServerSocket(9876);
		} catch (IOException e) {
			System.err.println("SERVIDOR >>> Error");
			return;
		}
		while (true) {
			Socket conexion = socketEscucha.accept();
			System.err.println("SERVIDOR >>> Conexion recibida!");
			
			InputStream is = conexion.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader bf = new BufferedReader(isr);
			System.err.println("SERVIDOR >>> Lee datos para la operacion");
			
			String operator = bf.readLine().toLowerCase();
			String num1 = bf.readLine();
			String num2 = bf.readLine();
			System.err.println("SERVIDOR >>> Realiza la operacion");
			
			Integer result = calcular(operator, num1, num2);
			System.err.println("SERVIDOR >>> Devuelve resultado");
			
			OutputStream os = conexion.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write(result.toString() + "\n");
			pw.flush();
			System.err.println("SERVIDOR >>> Espera nueva peticion");
		}
	}
}
