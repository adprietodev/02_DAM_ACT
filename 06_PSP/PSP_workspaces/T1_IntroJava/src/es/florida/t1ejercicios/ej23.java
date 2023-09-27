package es.florida.t1ejercicios;

import java.util.ArrayList;
import java.util.Scanner;

public class ej23 {

	public static void main(String[] args) {
		Scanner keyBoard = new Scanner(System.in);
		System.out.println("Indicame que nombres quieres guardar (El programa terminara cuando introduzcas un 0): ");
		
		ArrayList<String> names = new ArrayList<String>();
		String name = keyBoard.nextLine();
		
		while(!name.equals("0")) {
			names.add(name);
			name = keyBoard.nextLine();
		}
		
		System.out.println("Los nombres registrados son: ");
		
		for(int i=0; i<names.size();i++) {
			System.out.println((i+1)+".- "+names.get(i));
		}
		
		keyBoard.close();
	}

}
