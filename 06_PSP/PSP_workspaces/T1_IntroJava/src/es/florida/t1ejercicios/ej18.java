package es.florida.t1ejercicios;

import java.util.Scanner;

public class ej18 {

	public static boolean minChar = false;
	public static boolean num = false;
	public static boolean upLetter = false;
	
	public static void main(String[] args) {
		
		Scanner keyBoard = new Scanner(System.in);
		System.out.println("Indicame una contraseña que quieras asignar a tu cuenta.");
		System.out.println("Recuerda que debe tener minimo 5 caracteres, 1 numero y una letra mayuscula.");
		String pass1;
		String pass2;
		
		do {
			pass1 = keyBoard.nextLine();
			System.out.println("Repite la contraseña: ");
			pass2 = keyBoard.nextLine();
			
			if(pass1.equals(pass2)) {
				checkPass(pass1);
				
				if(!validPass()) {
					System.out.println("No cumple los requisitos");
					if(minChar == false) System.out.println("- Los caracteres no son suficientes, por favor inserta minimo 5.");
					if(num == false) System.out.println("- No contiene ningún numero.");
					if(upLetter == false) System.out.println("- No contiene letra mayuscula.");
					System.out.println("Introduce una contraseña valida.");
				}
			} else {
				System.out.println("Las contraseñas no coinciden, intentalo de nuevo");
			}
			
		} while(!validPass());
		
		System.out.println("Contraseña valida.");
		
		keyBoard.close();

	}
	
	/**
	 * Metodo para revisar que cumple las caracteristicas necesarias.
	 * @param Pasamos el parametro de pass.
	 * */
	
	public static void checkPass(String pass) {
		
		char c;
		
		if(pass.length() >= 5) minChar = true;
		
		for(int i=0; i < pass.length(); i++) {
			
			c = pass.charAt(i);
			
			if(Character.isDigit(c)) num = true;
			if(Character.isUpperCase(c)) upLetter=true;
			
			if(num == true && upLetter == true) break;
			
		}
	}
	/**
	 * Metodo para revisar que la contraseña es valida.
	 * @return retornamos booleano para validar
	 * */
	
	public static boolean validPass() {
		
		if(minChar == true && num == true && upLetter == true) {
			return true;
		} else {
			return false;
		}
	}

}
