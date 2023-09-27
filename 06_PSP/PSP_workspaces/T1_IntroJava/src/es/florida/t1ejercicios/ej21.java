package es.florida.t1ejercicios;

import java.util.Scanner;

public class ej21 {

	public static void main(String[] args) {
		
		Scanner keyBoard = new Scanner(System.in);
		System.out.println("Indicame 5 nombres que quieras almacenar: ");
		
		String[] names = new String[5];
		
		for(int i=0; i<5; i++) {
			names[i] = keyBoard.nextLine();
		}
		System.out.println("Los nombres registrados son: ");
		
		for(int i=0; i<5; i++) {
			System.out.println((i+1)+".- "+names[i]);
		}
		keyBoard.close();

	}

}
