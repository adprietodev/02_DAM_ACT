package es.florida.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Usuario implements Runnable {

	private String user;
	private Socket socket;
	private PrintWriter pw;
	private BufferedReader bfR;
	private Servidor server;

	private String messageIn;
	private String messageOut;
	private boolean send;

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
	}

	@Override
	public void run() {

		boolean exit = false;
		// TODO Auto-generated method stub

		while (!exit || this.messageIn != null) {

			try {
				send = false;
				messageIn = bfR.readLine();
				if (this.messageIn != null) {
					
					String[] splitMess = messageIn.split(" >>> ");

					if (messageIn != null) {
						if (splitMess[1].equals("?") && !send) {
							for (Usuario user : server.users) {
								messageOut += user.user + " | ";
							}
							System.err.println("Solicitud usuarios conectados: " + messageOut);
							pw.write("Usuarios conectados: " + messageOut + "\n");
							pw.flush();
							send = true;
						}

						if (splitMess[1].toLowerCase().equals("exit") && !send) {
							System.err.println("Usuario desconectado: "+this.user);
							pw.write("exit"+"\n");
							pw.flush();
							send = true;
							exit = true;
							server.users.remove(this);
							break;
						}

						// Privado
						synchronized (server.users) {
							for (Usuario userS : server.users) {
								if (messageIn.contains("@" + userS.user)) {
									String messMod = messageIn.replace(this.user + " >>> " + "@" + userS.user,
											this.user + " (privado) >>> ");
									System.err.println(messageIn);
									userS.pw.write(messMod + "\n");
									userS.pw.flush();
									send = true;
								}
							}
						}

						// Para todos
						if (!send) {
							synchronized (server.users) {
								for (Usuario userS : server.users) {
									if (userS != this) {
										System.err.println(messageIn);
										userS.pw.write(messageIn + "\n");
										userS.pw.flush();
									}
								}
							}

						}

					}
				} else {
					System.err.println("Usuario desconectado: "+this.user);
					exit = true;
					server.users.remove(this);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * Metodo que nos devuelve el nombre del usuario.
	 * @return
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Metodo para declarar el nombre de usuario de la clase.
	 * @param user
	 */
	public void setUser(String user) {
		this.user = user;
	}

}
