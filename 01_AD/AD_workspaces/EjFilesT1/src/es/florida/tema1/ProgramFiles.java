package es.florida.tema1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProgramFiles {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		//Solicitamos nombre del directorio que quiere buscar dentro del programa.
		Scanner insKB = new Scanner(System.in);
		
		System.out.print("¿Que directorio quieres ver? ");
		
		File nameDir = new File(insKB.nextLine());
		
		
		System.out.print("¿Que extensiones quieres buscar? ");
		
		String nameExt = insKB.nextLine();
		
		// Revisamos si rellena el tipo de extensión y si es así realiza una acción o otra.
		String[] listFiles;
		
		if(nameExt == "") {
			listFiles = nameDir.list();
		} else {
			listFiles = nameDir.list(new FilterExtension(nameExt));
		}
		
		
		// Comprobamos si el directorio que nos facilita el usuario existe
		if(nameDir.exists()) {
			
			System.out.println("- Información del directorio -");
			System.out.println("Nombre: "+nameDir.getName());
			System.out.println("Ruta relativa: "+nameDir.getPath());
			System.out.println("Ruta absoluta: "+nameDir.getAbsolutePath());
			System.out.println("Se puede leer: "+nameDir.canRead());
			System.out.println("Se puede escribir: "+nameDir.canWrite());
			System.out.println("El contenido es(Si has especificado extensión solo aparecerán los archivos con esa extensión): ");
			
			for(int i = 0; i < listFiles.length; i++) {
				
				System.out.println((i+1)+". "+listFiles[i]);
			}
			
		} else {
			System.out.print("El directorio " + nameDir.getName() + " no existe");
		}
		
		Scanner lec;
		
		try {
			lec = new Scanner(new File("passtest.txt"));
			
			while(lec.hasNext()) {
				System.out.println(lec.next());
			}
			
			/*FileReader fichero = new FileReader("passtest.txt");
			int linea = fichero.read();
			ArrayList<Character> caracteres = new ArrayList<Character>();
			while(linea != -1) {
				System.out.println((char)linea);
				linea = fichero.read();
			}
			
			fichero.close();*/
		} catch(Exception e) {
			System.out.println(e);
		}
		
		
		
	}

}
