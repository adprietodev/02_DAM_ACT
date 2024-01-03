package es.florida.miniproyectoT4;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {
		Scanner kB =  new Scanner(System.in);
		String host = "localhost";
		int puertoDestino = 1234;
		
		try {
			Socket socket = new Socket(host,puertoDestino);
			
			ObjectInputStream inObject = new ObjectInputStream(socket.getInputStream());
			Password pass = (Password) inObject.readObject();
			System.out.println("CLIENTE >>> Recibe objeto del servidor");
			System.out.println("Introduce una contraseña:");
			pass.setFlatText(kB.nextLine());
			
			ObjectOutputStream outObject =  new ObjectOutputStream(socket.getOutputStream());
			outObject.writeObject(pass);
			System.out.println("CLIENTE >>> Envia objeto con una contraseña plana...");
			
			InputStream is =  socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String question =  br.readLine();
			System.out.println("CLIENTE >>> Recibe pregunta sobre tipo de encriptación...");
			
			System.out.println(question);
			String res = kB.nextLine();
			
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write(res+"\n");
			pw.flush();
			System.out.println("CLIENTE >>> Envia la respuesta sobre tipo de encriptación...");
			
			inObject = new ObjectInputStream(socket.getInputStream());
			pass = (Password) inObject.readObject();
			System.out.println("CLIENTE >>> Recibe objeto con contraseña encriptada...");
			
			System.out.println("Contraseña sin encriptar: "+pass.getFlatText());
			System.out.println("Contraseña encriptada: "+pass.getEncriptText());
			
			inObject.close();
			os.close();
			is.close();
			outObject.close();
			socket.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
