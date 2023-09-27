package es.florida.t1ejercicios;

import java.util.Scanner;

public class ej15 {

	public static void main(String[] args) {
		
		Scanner keyBoard = new Scanner(System.in);
		System.out.println("Indicame el radio para calcular el diamtero y el area ");
		int radio = Integer.parseInt(keyBoard.nextLine());
		
		double volumen = (4/3)*Math.PI*Math.pow(radio, 3);
		
		System.out.println("Volumen de la esfera: "+String.format("%.3f", volumen));
		
		keyBoard.close();

	}

}
