package es.florida.tema1;

import java.io.File;
import java.util.Scanner;
import java.util.Date;

public class MiniProyecto_01 {

	static Scanner keyBoard = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		String directorio = args[0];
		File dir = new File(directorio);
		
		String name;
		String optNum = "0";
		System.out.println("BIENVENIDO AL COMPROBADOR DE ARCHIVOS");	
		
		while(!optNum.equals("6")) {
					System.out.println("------------------------------------------------");
			System.out.println("Ahora mismo tenemos el directorio/fichero "+directorio+" pasado por paramentro en  arg[0]");
			System.out.println("------------------------------------------------");
			System.out.println("1.- Información.");
			System.out.println("2.- Crear carpeta.");
			System.out.println("3.- Crear fichero.");
			System.out.println("4.- Eliminar fichero/directorio.");
			System.out.println("5.- Renombrar fichero/directorio.");
			System.out.println("6.- Salir");
			System.out.println("¿Que quieres realizar?(Indica el numero)");
			
			optNum = keyBoard.nextLine();
			
			switch(optNum) {
				case "1":
					getInformacion(dir);
					break;
				case "2":
					System.out.println("¿Que nombre quieres ponerle al directorio?");
					name = keyBoard.nextLine();
					creaCarpeta(name);
					break;
				case "3":
					System.out.println("¿Que nombre quieres ponerle al fichero?");
					name = keyBoard.nextLine();
					creaFichero(name);
					break;
				case "4":
					System.out.println("Indicame el nombre del fichero/carpeta que quieras eliminar ");
					name = keyBoard.nextLine();
					elimina(name);
					break;
				case "5":
					System.out.println("¿Que archivo quieres renombrar?");
					name = keyBoard.nextLine();
					renombra(name);
					break;
				case "6":
					System.out.println("¡Hasta la próxima!");	
					break;
			}
		}
		
		keyBoard.close();

	}
	
	public static void getInformacion(File dF) {
		System.out.println("--- Información ---");
		System.out.println("Nombre: "+dF.getName());
		if(dF.isFile()) {
			System.out.println("Tipo: Fichero");
			System.out.println("Tamaño: "+dF.length()+" bytes");
		}else {
			System.out.println("Tipo: Directorio");
			System.out.println("Cantidad elementos: "+dF.list().length);
			System.out.println("Espacio libre: "+dF.getFreeSpace()/1024/1024/1024+" GB ");
			System.out.println("Espacio ocupado: "+(dF.getTotalSpace()/1024/1024/1024 - dF.getFreeSpace()/1024/1024/1024)+" GB ");
			System.out.println("Espacio Total: "+dF.getTotalSpace()/1024/1024/1024+" GB ");
		}
		System.out.println("Ubicación: "+dF.getAbsolutePath());
		System.out.println("Ultima fecha de modificación: "+ new Date(dF.lastModified()));
		System.out.println("Oculto: "+dF.isHidden());
	}
	
	public static void creaCarpeta(String name) {
		File cDir = new File(name);
		cDir.mkdir();
		System.out.println("Se ha creado el directorio "+cDir);
	}
	
	public static void creaFichero(String name) {
		File fDir = new File(name);
		try {
			fDir.createNewFile();
		} catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	public static void elimina(String name) {
		File dir = new File(name);
		if(dir.exists()) {
			dir.delete();
			System.out.println("El directorio/dichero se ha eliminado");
		} else {
			System.out.println("El directorio/dichero no existe");
		}
	}
	
	public static void renombra(String name) {
		File dir = new File(name);
		System.out.println("¿Que nombre quieres ponerle?");
		String newName = keyBoard.nextLine();
		if(dir.exists()) {
			dir.renameTo(new File(newName));
			System.out.println("El directorio/dichero renombrado");
		} else {
			System.out.println("El directorio/dichero no existe");
		}
	}

}
