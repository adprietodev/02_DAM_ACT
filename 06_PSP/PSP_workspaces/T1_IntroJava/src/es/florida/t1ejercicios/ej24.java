package es.florida.t1ejercicios;

import java.util.Scanner;

public class ej24 {

	public static void main(String[] args) {
		
		Scanner keyBoard = new Scanner(System.in);
		System.out.println("Indicame un numero si coincide con el que pienso podrás elegir un premio. ");
		
		int numKB= Integer.parseInt(keyBoard.nextLine());
		int numRn = (int)(Math.random()*11);
		
		int cPresent; 

		if(numKB == numRn) {
			System.out.println("Has acertado puedes elegir entre (Inserta el numero): ");
			System.out.println("1.Movil 2.Consola 3.Libro 4.Ticket de comida");
			cPresent = Integer.parseInt(keyBoard.nextLine());
			
			switch(cPresent) {
				case 1:
					System.out.println("¡Enhorabuena! Aquí tienes tu movil");
				break;
				case 2:
					System.out.println("¡Enhorabuena! Aquí tienes tu Consola");
				break;
				case 3:
					System.out.println("¡Enhorabuena! Aquí tienes tu Libro");
				break;
				case 4:
					System.out.println("¡Enhorabuena! Aquí tienes tu Ticket de comida");
				break;
				default:
					System.out.println("No has seleccionado una opción valida, por lo que deducimos que no quieres ningún premio. ¡Gracias por participar!");
				
			}
			
		} else {
			System.out.println("Lo siento los numeros no coinciden, los numeros han sido "+numKB+" y el que nosotros teniamos pensado es "+numRn);
		}
		
		keyBoard.close();
	}

}
