package es.florida.t1ejercicios;

import java.util.Scanner;

public class ej17 {

	public static void main(String[] args) {
		
		Scanner keyBoard = new Scanner(System.in);
		System.out.println("Indicame una contraseña que quieras asignar a tu cuenta.");
		System.out.println("Recuerda que debe tener minimo 5 caracteres, 1 numero y una letra mayuscula.");
		String pass = keyBoard.nextLine();
		
		boolean minChar = false;
		boolean num = false;
		boolean upLetter = false;
		
		char c;
		
		if(pass.length() >= 5) minChar = true;
		
		for(int i=0; i < pass.length(); i++) {
			
			c = pass.charAt(i);
			
			if(Character.isDigit(c)) num = true;
			if(Character.isUpperCase(c)) upLetter=true;
			
			if(num == true && upLetter == true) break;
			
		}
		
		if(minChar == true && num == true && upLetter == true) {
			System.out.println("Contraseña valida");
		} else {
			System.out.println("Contraseña no valida");
		}
		
		if(minChar == false) System.out.println("- Los caracteres no son suficientes, por favor inserta minimo 5.");
		if(num == false) System.out.println("- No contiene ningún numero.");
		if(upLetter == false) System.out.println("- No contiene letra mayuscula.");
		
		keyBoard.close();
		
	}

}
