package es.florida.t1ejercicios;

import java.util.Scanner;

public class ej14 {

	public static void main(String[] args) {
		
		Scanner keyBoard = new Scanner(System.in);
		System.out.println("Indicame el radio para calcular el diamtero y el area ");
		int radio = Integer.parseInt(keyBoard.nextLine());
		
		double diametro = 2*radio;
		double area = Math.PI*Math.pow(radio, 2);
		
		System.out.println("Diametro: "+String.format("%.3f", diametro));
		
		System.out.println("Area: "+String.format("%.3f", area));
		
		keyBoard.close();

	}

}
