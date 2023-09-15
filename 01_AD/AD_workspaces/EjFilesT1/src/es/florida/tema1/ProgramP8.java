package es.florida.tema1;

import java.io.*;
import java.util.Scanner;

public class ProgramP8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner insKB = new Scanner(System.in);
		
		System.out.print("Elije fichero ");
		
		File dir = new File(".");
		
		File nFile = new File(insKB.nextLine());
		File nFile2 = new File("Cpasstest.txt");
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		listar(dir);
		try {
			fis = new FileInputStream(nFile);
			fos = new FileOutputStream(nFile2);
			
			int c;
			
			while((c = fis.read()) != -1 ) {
				fos.write(c);
			}
			listar(dir);
			System.out.println("Se ha copiado y renombrado a "+ nFile2.getName());
			
			
			nFile2.delete();
			listar(dir);
			System.out.println("Se ha eliminado el archivo copiado");
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		
	}

	private static void listar(File dir) {
		System.out.println("Documentos que hay en el directorio");
		String[] listFiles = dir.list();
		for(int i = 0; i < listFiles.length; i++) {
			
			System.out.println(listFiles[i]);
		}
	}

}
