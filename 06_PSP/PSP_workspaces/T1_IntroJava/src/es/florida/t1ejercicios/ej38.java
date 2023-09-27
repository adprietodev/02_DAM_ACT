package es.florida.t1ejercicios;

import java.util.Scanner;

public class ej38 {

	public static void main(String[] args) {
		
		Scanner keyBoard = new Scanner(System.in);
		System.out.println("Indicame 2 numeros y te mostraremos el intervalo de numeros que hay entre los dos y si es numero primo o no."
				+  "Introduce primero el menor y luego el mayor");
		int num1 = Integer.parseInt(keyBoard.nextLine());
		int num2 = Integer.parseInt(keyBoard.nextLine());
		
		long startTime = System.nanoTime();
		
		calculo(num1,num2);
		
		long endTime = System.nanoTime();
		
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("El tiempo que ha tardado es: "+duration+"ms");

	}
	
	public static void calculo(int num1, int num2) {
		
		boolean primo;
		
		for(int i = num1; i <= num2; i++) { //Utilizamos este for para recorrer los numeros indicados
			if(i == 0 || i == 1 || i == 4) {
				primo = false;
			} else {
				primo = true;
				for(int j=2; j<=i/2;j++) { //Utilizamos para revisar si es primo
					if((i %j) == 0) {
						primo=false;
						break;
					}
				}
			}
			if(primo) {
				System.out.println("Numero "+i+" es primo");
			} else {
				System.out.println("Numero "+i+" no es primo");
				
			}
		}
	}

}
