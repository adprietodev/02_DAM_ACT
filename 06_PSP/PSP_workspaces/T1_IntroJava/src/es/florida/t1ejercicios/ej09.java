package es.florida.t1ejercicios;

import java.util.Scanner;

public class ej09 {

	public static void main(String[] args) {
		
		Scanner keyBoard = new Scanner(System.in);
		
		System.out.print("Indicame un numero del 1 al 12 ");
		
		int num = Integer.parseInt(keyBoard.nextLine());
		
		switch(num) {
			case 1:
				System.out.print("Este numero corresponde al mes de Enero");
			break;
			case 2:
				System.out.print("Este numero corresponde al mes de Febrero");
				break;
			case 3:
				System.out.print("Este numero corresponde al mes de Marzo");
				break;
			case 4:
				System.out.print("Este numero corresponde al mes de Abril");
				break;
			case 5:
				System.out.print("Este numero corresponde al mes de Mayo");
				break;
			case 6:
				System.out.print("Este numero corresponde al mes de Junio");
				break;
			case 7:
				System.out.print("Este numero corresponde al mes de Julio");
				break;
			case 8:
				System.out.print("Este numero corresponde al mes de Agosto");
				break;
			case 9:
				System.out.print("Este numero corresponde al mes de Septiembre");
				break;
			case 10:
				System.out.print("Este numero corresponde al mes de Octubre");
				break;
			case 11:
				System.out.print("Este numero corresponde al mes de Noviembre");
				break;
			case 12:
				System.out.print("Este numero corresponde al mes de Diciembre");
				break;
			default:
				System.out.print("Este numero no corresponde a ningún mes");
			
		}

	}

}
