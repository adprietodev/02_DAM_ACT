package es.florida.t1ejercicios;

import java.util.Scanner;

public class ej37 {

	public static void main(String[] args) {
		
		Scanner keyBoard = new Scanner(System.in);
		System.out.println("Indicame el tiempo que llevas trabajando como programador (A.M) ");
		double años = Double.parseDouble(keyBoard.nextLine());
		
		medirNivel(años);

	}
	
	public static void medirNivel(double años) {
		if(años < 1.0f) System.out.println("Desarrollador Junior L1 - Salario: 15.000 - 18.000");
		if(años > 1.0f && años < 3.0f) System.out.println("Desarrollador Junior L2 - Salario: 18.000 - 22.000");
		if(años > 3.0f && años < 5.0f) System.out.println("Desarrollador Senior L1 - Salario: 22.000 - 28.000");
		if(años > 5.0f && años < 8.0f) System.out.println("Desarrollador Senior L2 - Salario: 28.000 - 36.000");
		if(años > 8.0f) System.out.println("Analista / Arquitecto. Salario a convertir en base a rol");
	}

}
