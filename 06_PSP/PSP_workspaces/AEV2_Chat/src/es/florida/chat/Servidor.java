package es.florida.chat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

	public List<String[]> autorizedUsers;
	public List<Usuario> users ;
	private PrintWriter pw;
	private BufferedReader bfR;
	private ServerSocket socketEscucha;
	private Socket conexion;

	
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

			server.pw = new PrintWriter(server.conexion.getOutputStream());
			server.bfR = new BufferedReader(new InputStreamReader(server.conexion.getInputStream()));

			System.err.println("SERVIDOR >>> Realización autentificación");

			server.authenticated(server);

		}
	}

	/**
	 * Metodo que se utiliza para coger del documento los usuarios autorizados
	 * @return List<String[]> con el usuario y contraseña.
	 */
	private static List<String[]> getAutorizedUsers(){

		List<String[]> autorizedUsers = new ArrayList<>();

		try {
			FileReader fR = new FileReader("autorizados.txt");
			BufferedReader bR =  new BufferedReader(fR);

			String linea = "";

			while((linea = bR.readLine()) != null) {
				autorizedUsers.add(linea.split(";"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return autorizedUsers;
	}

	/**
	 * Metodo que utilizamos para saber si el usuario esta autentificado ya o no.
	 * @param server
	 */
	private void authenticated(Servidor server) {
		boolean checkAuthenticated = false;

		while(!checkAuthenticated) {

			String received;
			try {
				received = bfR.readLine();
				String[] userPass = received.split(";");


				for(String[] autorized : autorizedUsers) {

					boolean isConnected = false;

					if(userPass[0].equals(autorized[0])) {
						if(users != null) {
							for(Usuario user : users) {
								if(user.getUser().equals(userPass[0])) {
									isConnected = true;
									System.err.println("Usuario ya logeado desde otro cliente");
									pw.write("false\n");
									pw.flush();
								}
							}
						}
						if(!isConnected) {
							if(autorized[0].equals(userPass[0]) && autorized[1].equals(userPass[1])) {

								Usuario user = new Usuario(autorized[0],conexion, server);
								server.users.add(user);
								new Thread(user).start();

								System.err.println("SERVIDOR >>> Conexion recibida --> Lanza hilo");

								System.err.println("AUTORIZADO");
								pw.write("true\n");
								pw.flush();

								checkAuthenticated = true;

							} else {
								System.err.println("NO AUTORIZADO");
								pw.write("false\n");
								pw.flush();
							}

						}
					}
				}

				if(!checkAuthenticated) {
					System.err.println("SERVIDOR >>> ERROR AUTENTIFICACIÓN");
					pw.write("false\n");
					pw.flush();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}


		}

	}

}
