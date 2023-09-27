package es.florida.t1ejercicios;

import java.util.Scanner;

public class ej05 {

	public static void main(String[] args) {
		
		Scanner keyBoard = new Scanner(System.in);
		
		int num1;
		int num2;
		
		do {
			
			System.out.print("Dime un mumero ");
			
			num1 = Integer.parseInt(keyBoard.nextLine());
			
			System.out.print("Dime otro, por favor ");
			
			num2 = Integer.parseInt(keyBoard.nextLine());
			
			if(num1 == num2) {
				System.out.println("Los numeros son iguales, finalizamos programa");
			}else {
				System.out.println("Los numeros no coinciden");
			}
			
		}while(num1 != num2);
		
		keyBoard.close();

	}

}
