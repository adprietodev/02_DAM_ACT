package es.florida.t1ejercicios;

import java.util.Scanner;

public class ej07 {

	public static void main(String[] args) {
		
		int sum = 0;
		Scanner keyBoard = new Scanner(System.in);
		int nums[] = new int[5];
		
		System.out.println("Indicame 5 numeros para sumarlos, pulsa ENTER para insertar nuevo numero.");
		
		for(int i=0; i < 5; i++) {
			nums[i] = Integer.parseInt(keyBoard.nextLine());
		}
		
		for(int i=0; i < 5; i++) {
			sum += nums[i];
		}
		
		System.out.println("La suma de los numeros es: "+sum);

	}

}
