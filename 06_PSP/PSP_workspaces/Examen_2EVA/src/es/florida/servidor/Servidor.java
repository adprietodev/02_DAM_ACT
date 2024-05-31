package es.florida.servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

	public List<String[]> autorizedUsers;
	public List<Usuario> users;
	private PrintWriter pw;
	private BufferedReader bfR;
	private ServerSocket socketEscucha;
	private Socket conexion;
	private OutputStream os;
	private InputStream iS;
	private InputStreamReader iSR;

	public Servidor() {
		autorizedUsers = getAutorizedUsers();
		users = new ArrayList();
		try {
			socketEscucha = new ServerSocket(9876);

		} catch (IOException e) {
			System.err.println("SERVIDOR >>> Error");
			return;
		}

	}

	public static void main(String[] args) throws IOException {

		Servidor server = new Servidor();

		System.err.println("SERVIDOR >>> Arranca el servidor, espera peticion");

		while (true) {

			System.err.println("SERVIDOR >>> espera peticion");
			server.conexion = server.socketEscucha.accept();

			server.os = server.conexion.getOutputStream();
			server.pw = new PrintWriter(server.os);

			server.iS = server.conexion.getInputStream();
			server.iSR = new InputStreamReader(server.iS);
			server.bfR = new BufferedReader(server.iSR);
			String res = server.bfR.readLine();
			System.err.println("SERVIDOR >> Recibe respuesta ");
			String acction = "";
			if (res.contains(";"))
				acction = res.split(";")[0];

			if (acction.equals("REGISTRO")) {

				// Logica registro
				FileWriter fW = new FileWriter(new File("Usuarios_autorizados.txt"), true);
				BufferedWriter bW = new BufferedWriter(fW);
				bW.write(res.split(";")[1] + ";" + res.split(";")[2]);
				bW.newLine();

				bW.close();
				fW.close();

				System.err.println("SERVIDOR >> Registramos usuario ");

				server.pw.write("true\n");
				server.pw.flush();

				server.iS = server.conexion.getInputStream();
				server.iSR = new InputStreamReader(server.iS);
				server.bfR = new BufferedReader(server.iSR);
				res = server.bfR.readLine();
				// System.out.println(res);
				System.err.println("SERVIDOR >> Esperamos login");

			}

			if (res.equals("LOGIN")) {

				server.iS = server.conexion.getInputStream();
				server.iSR = new InputStreamReader(server.iS);
				server.bfR = new BufferedReader(server.iSR);
				System.err.println("SERVIDOR >> Recibe usuario y contraseña...");
				System.err.println("SERVIDOR >>> Realización autentificación");
				server.authenticated(server);
			}

		}
	}

	/**
	 * Metodo que se utiliza para coger del documento los usuarios autorizados
	 * 
	 * @return List<String[]> con el usuario y contraseña.
	 */
	private static List<String[]> getAutorizedUsers() {

		List<String[]> autorizedUsers = new ArrayList<>();

		try {
			FileReader fR = new FileReader("Usuarios_autorizados.txt");
			BufferedReader bR = new BufferedReader(fR);

			String linea = "";

			while ((linea = bR.readLine()) != null) {
				autorizedUsers.add(linea.split(";"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return autorizedUsers;
	}

	private void authenticated(Servidor server) {
		boolean checkAuthenticated = false;

		String received = "";
		try {
			received = bfR.readLine();
			//System.out.println(received);
			String[] userPass = received.split(";");

			for (String[] autorized : autorizedUsers) {

				if (autorized[0].equals(userPass[0]) && autorized[1].equals(userPass[1])) {

					Usuario user = new Usuario(autorized[0], conexion, server);
					server.users.add(user);
					new Thread(user).start();

					System.err.println("SERVIDOR >>> Conexion recibida --> Lanza hilo");

					System.err.println("AUTORIZADO");
					pw.write("204\n");
					pw.flush();

					checkAuthenticated = true;

				}
			}

			if (!checkAuthenticated) {
				System.err.println("NO AUTORIZADO");
				pw.write("401\n");
				pw.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

	}

}
