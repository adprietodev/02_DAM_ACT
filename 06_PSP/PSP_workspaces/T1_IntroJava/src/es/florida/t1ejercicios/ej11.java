package es.florida.t1ejercicios;

import java.util.Scanner;

public class ej11 {

	public static void main(String[] args) {
		
		char letra[] = {'T','R','W','A','G','M','Y','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
		
		Scanner keyBoard = new Scanner(System.in);
		
		System.out.print("Indicame tu numero del DNI y te indicare que letra corresponde ");
		
		int dni = Integer.parseInt(keyBoard.nextLine());
		
		System.out.print(dni+"-"+letra[dni%23]);
		
	}

}
