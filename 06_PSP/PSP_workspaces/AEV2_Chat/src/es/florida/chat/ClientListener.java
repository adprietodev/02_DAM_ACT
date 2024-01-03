package es.florida.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientListener implements Runnable {

	private Socket socket;
	private BufferedReader bfR;
	private Cliente client;

	public ClientListener(Socket socket, Cliente client) {
		this.socket = socket;
		this.client = client;
		try {
			bfR = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!client.exit) {
			try {
				String messageIn =  bfR.readLine();
				if(messageIn != null) {
					System.out.println(messageIn);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
