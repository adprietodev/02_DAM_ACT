package es.florida.t1ejercicios;

import java.util.Scanner;

public class ej34 {

	public static void main(String[] args) {
		
		double factorial = 1;
		double num = 15;
		
		while(num != 0) {
			factorial *= num;
			num--;
		}
		
		System.out.println(factorial);
	}

}
