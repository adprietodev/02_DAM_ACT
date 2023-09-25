package es.florida.t1ejercicios;

import java.util.Scanner;

public class ej04 {

	public static void main(String[] args) {
		
		Scanner keyBoard = new Scanner(System.in);
		
		System.out.print("Dime un mumero ");
		
		int num1 = Integer.parseInt(keyBoard.nextLine());
		
		System.out.print("Dime otro para poder compararlo ");
		
		int num2 = Integer.parseInt(keyBoard.nextLine());
		
		if (num1 < num2) {
			System.out.print("El "+num1+" es menor que "+num2);
		}
		if (num1 > num2) {
			System.out.print("El "+num1+" es mayor que "+num2);
		}
		if (num1 == num2) {
			System.out.print("El "+num1+" es igual que "+num2);
		}

	}

}
