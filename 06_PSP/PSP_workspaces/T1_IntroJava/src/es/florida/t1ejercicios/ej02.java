package es.florida.t1ejercicios;

import java.util.Scanner;

public class ej02 {

	public static void main(String[] args) {
		
		Scanner keyBoard = new Scanner(System.in);
		
		System.out.print("Buenas ¿Puedes indicarme un nombre para dirigirme a ti? ");
		
		String name = keyBoard.nextLine();
		
		System.out.print("Hola "+name+", bienvenido");

		keyBoard.close();
	}

}
