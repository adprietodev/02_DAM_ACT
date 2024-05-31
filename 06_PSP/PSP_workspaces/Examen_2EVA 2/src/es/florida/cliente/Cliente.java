package es.florida.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Cliente {

	private Scanner kB;
	private BufferedReader bfR;
	private PrintWriter pw;
	private OutputStream oS;
	private InputStream iS;
	private InputStreamReader iSR;

	private String menssage;
	public boolean exit = false;
	private String user;
	private String password;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Cliente cliente = new Cliente();

		InetSocketAddress direction = new InetSocketAddress("localhost", 9876);
		Socket socket = new Socket();

		cliente.kB = new Scanner(System.in);
		

		try {

			socket.connect(direction);
			cliente.oS = socket.getOutputStream();
			cliente.pw = new PrintWriter(cliente.oS);

			System.out.println("Indicame que quieres realizar:");
			System.out.println("1. Login");
			System.out.println("2. Registro");
			String selection = cliente.kB.nextLine();

			if (selection.equals("2")) {

				System.out.println("--Registro-- ");
				System.out.println("Introiduce el usuario: ");
				String user = cliente.kB.nextLine();

				System.out.println("Introiduce la contraseña: ");
				String password = cliente.kB.nextLine();

				
				cliente.pw.write("REGISTRO" + ";" + user + ";" + password + "\n");
				cliente.pw.flush();
				
				cliente.iS = socket.getInputStream();
				cliente.iSR = new InputStreamReader(cliente.iS);
				cliente.bfR = new BufferedReader(cliente.iSR);
				
				String registered = cliente.bfR.readLine();
				
				if(Boolean.parseBoolean(registered)) System.out.println("CLIENTE >> Usuario registrado ");
				
			}

			System.out.println("CLIENTE >> Solicitud de login LOGIN ");
			cliente.pw.write("LOGIN\n");
			cliente.pw.flush();

			System.out.println("Introiduce el usuario: ");
			cliente.user = cliente.kB.nextLine();

			System.out.println("Introiduce la contraseña: ");
			cliente.password = cliente.kB.nextLine();

			cliente.oS = socket.getOutputStream();
			cliente.pw = new PrintWriter(cliente.oS);
			cliente.pw.write(cliente.user + ";" + cliente.password + "\n");
			cliente.pw.flush();

			cliente.iS = socket.getInputStream();
			cliente.iSR = new InputStreamReader(cliente.iS);
			cliente.bfR = new BufferedReader(cliente.iSR);

			String recived = cliente.bfR.readLine();

			if (recived.equals("204")) {
				System.out.println("Usuario autorizado");
				System.out.println("Indica el numero de lineas que quieres escribir ");
				String numLineas = cliente.kB.nextLine();
				cliente.pw.write(numLineas+"\n");
				cliente.pw.flush();
				
				cliente.iSR = new InputStreamReader(cliente.iS);
				cliente.bfR = new BufferedReader(cliente.iSR);
				
				recived = cliente.bfR.readLine();
				System.out.println("CLIENTE >> Respuesta del servidor "+recived);
				
				for(int i= 0; i < Integer.parseInt(numLineas); i++) {
					System.out.println("Insertar linea: ");
					cliente.pw.write(cliente.kB.nextLine()+"\n");
					cliente.pw.flush();
				}
				
			} else if (recived.equals("401")) {
				System.err.println("Usuario no autorizado");
				socket.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para coger el ultimo mensaje enviado.
	 * 
	 * @return devolvemos un string con el ultimo mensaje escrito.
	 */
	public String getMenssage() {
		return menssage;
	}

	/**
	 * Metodo para coger la hora y fecha a la que se envia el mensaje.
	 * 
	 * @return la fecha y hora en formato String.
	 */
	public String getTimesTamp() {
		LocalDateTime fechaHoraActual = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String fechaHoraFormateada = fechaHoraActual.format(formatter);

		return fechaHoraFormateada;
	}
}
