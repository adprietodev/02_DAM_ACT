package es.florida.ej_t4;

import java.io.*;
import java.net.*;

public class GestorDescargas {

	public void descargarArchivo(String url_desc, String nameFile) {
		System.out.println("Descargando " + url_desc);

		try {
			URL url = new URL(url_desc);
			// Leer
			InputStream iS = url.openStream();
			InputStreamReader iSReader = new InputStreamReader(iS);
			BufferedReader bR = new BufferedReader(iSReader);
			// Escribir
			FileWriter fW = new FileWriter(nameFile);
			BufferedWriter bW = new BufferedWriter(fW);
			String linea;
			while ((linea = bR.readLine()) != null) {
				bW.write(linea);
				bW.newLine();
				// System.out.println(linea);
			}
			bW.close();
			fW.close();
			bR.close();
			iSReader.close();
			iS.close();

		} catch (MalformedURLException e) {
			System.out.println("URL mal escrita!");
		} catch (IOException e) {
			System.out.println("Fallo en la lectura del fichero");
		}
	}

	private void descargarImagen(String urlImagen, String copiaImg) {
		System.out.println("Descargando " + urlImagen);

		try {
			URL url = new URL(urlImagen);
			InputStream inputStream = url.openStream();
			try (FileOutputStream fos = new FileOutputStream(copiaImg)) {
				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					fos.write(buffer, 0, bytesRead);
				}
			}
			inputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestorDescargas gD = new GestorDescargas();

//		String url = "http://localhost/dashboard"+"/web/Libros.csv";
//		String fichero = "prueba.txt";
//		gD.descargarArchivo(url, fichero);

		String urlImg = "http://localhost:8080/imagenes/djarii_rabbit.jpeg";
		String img = "copia-img.png";
		gD.descargarImagen(urlImg, img);

	}

}
