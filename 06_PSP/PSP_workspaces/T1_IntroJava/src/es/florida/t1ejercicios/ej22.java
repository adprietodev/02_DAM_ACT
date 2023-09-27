package es.florida.t1ejercicios;

import java.util.ArrayList;
import java.util.Scanner;

public class ej22 {

	public static void main(String[] args) {
		
		Scanner keyBoard = new Scanner(System.in);
		System.out.println("Indicame que nombres quieres guardar (El programa terminara cuando introduzcas un 0): ");
		
		ArrayList<String> names = new ArrayList<String>();
		String name = keyBoard.nextLine();
		
		while(!name.equals("0")) {
			names.add(name);
			name = keyBoard.nextLine();
		}
		
		keyBoard.close();
		
	}

}
