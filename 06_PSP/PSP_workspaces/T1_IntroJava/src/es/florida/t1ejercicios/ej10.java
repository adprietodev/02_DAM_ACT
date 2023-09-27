package es.florida.t1ejercicios;

import java.util.Scanner;

public class ej10 {

	public static void main(String[] args) {
		
		Scanner keyBoard = new Scanner(System.in);
		
		System.out.print("Indicame un numero del 1 al 12 ");
		
		int num = Integer.parseInt(keyBoard.nextLine());
		
		while(num < 1 || num > 12) {
			System.out.println("El numero debe estar entre 1 y 12 repitelo de nuevo ");
			num = Integer.parseInt(keyBoard.nextLine());
		}
		
		if(num == 1) System.out.print("Este numero corresponde al mes de Enero");
		if(num == 2) System.out.print("Este numero corresponde al mes de Febrero");
		if(num == 3) System.out.print("Este numero corresponde al mes de Marzo");
		if(num == 4) System.out.print("Este numero corresponde al mes de Abril");
		if(num == 5) System.out.print("Este numero corresponde al mes de Mayo");
		if(num == 6) System.out.print("Este numero corresponde al mes de Junio");
		if(num == 7) System.out.print("Este numero corresponde al mes de Julio");
		if(num == 8) System.out.print("Este numero corresponde al mes de Agosto");
		if(num == 9) System.out.print("Este numero corresponde al mes de Septiembre");
		if(num == 10) System.out.print("Este numero corresponde al mes de Octubre");
		if(num == 11) System.out.print("Este numero corresponde al mes de Noviembre");
		if(num == 12) System.out.print("Este numero corresponde al mes de Diciembre");
		
		keyBoard.close();

	}

}
