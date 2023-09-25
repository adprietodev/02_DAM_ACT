package es.florida.t1ejercicios;

import java.util.Scanner;

public class ej13 {

	public static void main(String[] args) {
		
		
		Scanner keyBoard = new Scanner(System.in);
		System.out.println("Indicame los grados centigrados para pasarlos a fahrenheit");
		int celsius = Integer.parseInt(keyBoard.nextLine());
		
		double fahrenheit = (celsius * 1.8f)+32;
		
		System.out.println(String.format("%.1f", fahrenheit)+" grados fahrenheit");

	}

}
