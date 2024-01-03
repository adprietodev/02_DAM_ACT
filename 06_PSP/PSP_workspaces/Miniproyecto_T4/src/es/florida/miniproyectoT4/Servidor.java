package es.florida.miniproyectoT4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

	public static void main(String[] args) {
		System.err.println("SERVIDOR >>> Arranca el servidor, espera peticion");

		while (true) {
			try {
				ServerSocket server = new ServerSocket(1234);
				System.err.println("SERVIDOR >> Escuchando...");
				Socket cliente = server.accept();

				ObjectOutputStream outObject = new ObjectOutputStream(cliente.getOutputStream());
				Password pass = new Password();
				outObject.writeObject(pass);
				System.err.println("SERVIDOR >> Envia objeto de contraseña...");

				ObjectInputStream inObject = new ObjectInputStream(cliente.getInputStream());
				pass = (Password) inObject.readObject();
				System.err.println("SERVIDOR >> Recibe el objeto de contraseña...");

				OutputStream os = cliente.getOutputStream();
				PrintWriter pw = new PrintWriter(os);
				
				pw.write("¿Que tipo de encriptación quieres?(1/2) 1.-Bajo nivel. 2.-MD5" + "\n");
				pw.flush();
				System.err.println("SERVIDOR >> Envia pregunta sobre tipo encriptación....");

				InputStream is = cliente.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String res = br.readLine();
				System.err.println("SERVIDOR >> Recibe respuesta de la pregunta...");

				pass.setEncriptText(encriptPassword(pass, res));

				outObject = new ObjectOutputStream(cliente.getOutputStream());
				outObject.writeObject(pass);
				System.err.println("SERVIDOR >> Envia objeto con contraseña encriptada....");

				outObject.close();
				is.close();
				os.close();
				cliente.close();
				server.close();
			} catch (Exception e) {
				System.err.println("SERVIDOR >>> Error");
				return;

			}
		}

	}

	private static String encriptPassword(Password pass, String res) {
		String encriptPass = "";
		String flatPass = pass.getFlatText();

		if (res.equals("1")) {
			for (int i = 0; i < flatPass.length(); i++) {
				char caracter = flatPass.charAt(i);
				encriptPass += nextChar(caracter);
			}
		}

		if (res.equals("2")) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");

				md.update(flatPass.getBytes());

				byte[] byteData = md.digest();

				StringBuilder sb = new StringBuilder();

				for (byte b : byteData) {
					sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
				}

				encriptPass = sb.toString();

			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				encriptPass = null;
			}
		}

		return encriptPass;
	}

	private static String nextChar(char caracter) {
		char nextCaracter = (char) (caracter + 1);

		if (!Character.isISOControl(nextCaracter) && !Character.isWhitespace(nextCaracter)) {
			return String.valueOf(nextCaracter);
		} else {
			return "*";
		}
	}

}
