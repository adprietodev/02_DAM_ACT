package es.florida.t1ejercicios;

import java.util.Scanner;

public class ej26 {

	public static int notas[] = new int[10];
	public static int countSus = 0;
	public static int countApro = 0;
	public static int countNot = 0;
	public static int countSobr = 0;
	public static int countMatr = 0;
	
	public static void main(String[] args) {
		
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
					sumSuspenso();
				break;
				case 5,6:
					sumAprovado();
				break;
				case 7,8:
					sumNotable();
				break;
				case 9:
					sumSobresaliente();
				break;
				case 10:
					sumMatricula();
				break;
			}
		}
		
		System.out.println("Los suspensos son: "+countSus);
		System.out.println("Los aprovados son: "+countApro);
		System.out.println("Los notables son: "+countNot);
		System.out.println("Los sobresalientes son: "+countSobr);
		System.out.println("Las matriculas son: "+countMatr);
		
		keyBoard.close();
		
	}
	
	public static int sumSuspenso() {
		return countSus++;
	}
	public static int sumAprovado() {
		return countApro++;
	}
	public static int sumNotable() {
		return countNot++;
	}
	public static int sumSobresaliente() {
		return countSobr++;
	}
	public static int sumMatricula() {
		return countMatr++;
	}

}
