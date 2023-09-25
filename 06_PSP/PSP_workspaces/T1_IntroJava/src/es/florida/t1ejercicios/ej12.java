package es.florida.t1ejercicios;

import java.util.Scanner;

public class ej12 {

	public static void main(String[] args) {
		
		
		int notas[] = new int[10];
		int countSus = 0;
		int countApro = 0;
		int countNot = 0;
		int countSobr = 0;
		int countMatr = 0;
		
		Scanner keyBoard = new Scanner(System.in);
		
		System.out.print("Indicame 10 notas del 1 al 10, en caso de que me indiques un numero no valido te lo solicitare de nuevo");
		
		for(int i=0; i < 10; i++ ) {
			do {
				notas[i] = Integer.parseInt(keyBoard.nextLine());
				if(notas[i] < 1 || notas[i] > 10) System.out.println("Numero no valido por favor introducelo de nuevo");
			} while(notas[i] < 1 || notas[i] > 10);
		}
		
		for(int i=0; i < 10; i++ ) {
			switch(notas[i]) {
				case 1,2,3,4 :
					countSus++;
				break;
				case 5,6:
					countApro++;
				break;
				case 7,8:
					countNot++;
				break;
				case 9:
					countSobr++;
				break;
				case 10:
					countMatr++;
				break;
			}
		}
		
		System.out.println("Los suspensos son: "+countSus);
		System.out.println("Los aprovados son: "+countApro);
		System.out.println("Los notables son: "+countNot);
		System.out.println("Los sobresalientes son: "+countSobr);
		System.out.println("Las matriculas son: "+countMatr);

	}

}
