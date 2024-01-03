package es.florida.chat;

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
	private boolean connected = false;
	public boolean exit = false;
	private String user;
	private String password;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Cliente cliente = new Cliente();

		InetSocketAddress direction = new InetSocketAddress("localhost", 9876);
		Socket socket = new Socket();

		cliente.menssage = "";
		cliente.kB = new Scanner(System.in);

		try {

			socket.connect(direction);
			while (!cliente.connected) {

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

				String received = cliente.bfR.readLine();

				cliente.connected = Boolean.parseBoolean(received);
			}

			ClientListener cL = new ClientListener(socket, cliente);
			Thread listenerThread = new Thread(cL);
			listenerThread.start();

			System.out.println("Para escribir un mensaje presiona ENTER ");
			while (!cliente.exit) {

				String press = cliente.kB.nextLine();

				cliente.menssage = JOptionPane.showInputDialog("Escribe el mensaje que quieres enviar: ");

				if(cliente.menssage.toLowerCase().equals("exit")) {
					
					cliente.exit = true;
				} 
				
				String messOut = cliente.getTimesTamp()+": "+cliente.user+" >>> "+cliente.menssage;

				System.out.println(cliente.getTimesTamp()+": "+cliente.menssage);
				cliente.pw = new PrintWriter(socket.getOutputStream());
				cliente.pw.write(messOut + "\n");
				cliente.pw.flush();
				
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para coger el ultimo mensaje enviado.
	 * @return devolvemos un string con el ultimo mensaje escrito.
	 */
	public String getMenssage() {
		return menssage;
	}

	/**
	 * Metodo para coger la hora y fecha a la que se envia el mensaje.
	 * @return la fecha y hora en formato String.
	 */
	public String getTimesTamp() {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHoraFormateada = fechaHoraActual.format(formatter);

        return fechaHoraFormateada;
	}

}
