package es.florida.t1ejercicios;

import java.util.Scanner;

public class ej03 {

	public static void main(String[] args) {
		
		Scanner keyBoard = new Scanner(System.in);
		
		System.out.print("Dime un mumero ");
		
		int num1 = Integer.parseInt(keyBoard.nextLine());
		
		System.out.print("Dime otro para poder sumarlo ");
		
		int num2 = Integer.parseInt(keyBoard.nextLine());
		
		System.out.print("La suma es "+(num1+num2));

	}

}
