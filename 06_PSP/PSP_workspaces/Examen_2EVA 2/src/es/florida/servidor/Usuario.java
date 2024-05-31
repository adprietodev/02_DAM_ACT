package es.florida.servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Usuario implements Runnable {
	private String user;
	private Socket socket;
	private PrintWriter pw;
	private BufferedReader bfR;
	private Servidor server;

	private String messageIn;
	private String messageOut;
	private boolean send;
	
	private List<String> lineas;

	public Usuario(String user, Socket socket, Servidor server) {
		this.user = user;
		this.socket = socket;
		this.server = server;
		try {
			pw = new PrintWriter(socket.getOutputStream());
			bfR = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			send = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

		messageIn = "";
		messageOut = "";
		
		lineas = new ArrayList();
	}

	@Override
	public void run() {

		// TODO Auto-generated method stub
		try {
			messageIn = bfR.readLine();
			int numLines = Integer.parseInt(messageIn);
			System.out.println(numLines);
			File file = new File(user+"_"+getTimesTamp()+".txt");
			FileWriter fW = new FileWriter(new File(user+"_"+getTimesTamp()+".txt"), true);
			fW.close();
			
			
			pw.write("PREPARADO\n");
			pw.flush();
			
			for(int i = 0; i < numLines; i++) {
				
				messageIn = bfR.readLine();
				lineas.add(messageIn);
				System.err.println(messageIn);
				fW = new FileWriter(file, true);
				BufferedWriter bW = new BufferedWriter(fW);
				bW.write(messageIn);
				bW.newLine();

				bW.close();
				fW.close();
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getTimesTamp() {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMDDHHMMSS");
        String fechaHoraFormateada = fechaHoraActual.format(formatter);

        return fechaHoraFormateada;
	}

	/**
	 * Metodo que nos devuelve el nombre del usuario.
	 * 
	 * @return
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Metodo para declarar el nombre de usuario de la clase.
	 * 
	 * @param user
	 */
	public void setUser(String user) {
		this.user = user;
	}
}
