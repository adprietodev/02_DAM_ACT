package es.florida.t1ejercicios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ej36 {

	public static void main(String[] args) {
		
		pedirNumeros();

	}
	
	public static void pedirNumeros() {
		ArrayList<Integer> num = new ArrayList<Integer>();
		Scanner keyBoard = new Scanner(System.in);
		int sum = 0;
		
		System.out.println("Indicame 5 numeros enteros");
		for(int i=0;i<5;i++) {
			int numKB = Integer.parseInt(keyBoard.nextLine());
			num.add(numKB);
		}
		
		System.out.println("Los numeros que has puesto en orden son: ");
		for(int number : num) {
			sum += number;
			System.out.println(number);
		}
		
		Collections.reverse(num);
		System.out.println("La suma de estos numeros es:  "+sum);
		
		System.out.println("Los numeros que has puesto en orden inverso son: ");
		for(int number : num) {
			System.out.println(number);
		}
		
		keyBoard.close();
	}

}
