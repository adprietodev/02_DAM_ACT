package es.florida.t1ejercicios;

import java.util.Scanner;

public class ej33 {

	public static void main(String[] args) {
		
		Scanner keyBoard = new Scanner(System.in);
		
		System.out.print("Indicame un numero, realizaremos la suma de todos los pares hasta llegar a el.");
		int num = Integer.parseInt(keyBoard.nextLine());
		
		int sum = 0;
		
		for(int i= 0; i <= num; i++) {
			if(i%2 == 0) {
				sum += i;
			}
		}
		
		System.out.print("La suma es: "+sum);
		
		keyBoard.close();

	}

}
