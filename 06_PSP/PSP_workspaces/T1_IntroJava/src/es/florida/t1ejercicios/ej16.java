package es.florida.t1ejercicios;

import java.util.Scanner;

public class ej16 {

	public static void main(String[] args) {
		
		Scanner keyBoard = new Scanner(System.in);
		System.out.println("Indicame tu fecha de nacimiento para indicarte tu numero de la suerte (DD/MM/AAAA) ");
		String bornDate = keyBoard.nextLine();
		
		bornDate = bornDate.replace("/", "");
		
		String[] numsDate = new String[bornDate.length()];
		String[] splitSum = new String[2];
		int sum = 0;
		
		for(int i = 0; i < bornDate.length(); i++) {
			numsDate[i] = bornDate.substring(i, i+1);
		}
		
		
		
		for(int i = 0; i < numsDate.length; i++) {
			
			if(numsDate[i] != " " || numsDate[i] != null) {
				sum += Integer.parseInt(numsDate[i]);
			}
			
		}
		
		splitSum[0] = Integer.toString(sum).substring(0, 1);
		splitSum[1] = Integer.toString(sum).substring(1, 2);
		
		sum = Integer.parseInt(splitSum[0]) + Integer.parseInt(splitSum[1]);
		
		System.out.println("Tu numero de la suerte es: "+sum);
		
		keyBoard.close();

	}

}
