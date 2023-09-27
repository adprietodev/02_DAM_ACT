package es.florida.t1ejercicios;

import java.util.Scanner;

public class ej06 {

	public static void main(String[] args) {
		
		int i = 0;
		int sum = 0;
		Scanner keyBoard = new Scanner(System.in);
		int nums[] = new int[5];
		
		System.out.println("Indicame 5 numeros para sumarlos, pulsa ENTER para insertar nuevo numero.");
		while(i < 5) {
			
			nums[i] = Integer.parseInt(keyBoard.nextLine());
			i++;
		}
		
		i=0;
		
		while(i < 5) {
			
			sum += nums[i];
			i++;
			
		}
		
		System.out.println("La suma de los numeros es: "+sum);

		keyBoard.close();
	}

}
