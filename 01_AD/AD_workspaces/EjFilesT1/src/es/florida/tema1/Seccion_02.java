package es.florida.tema1;

import java.util.Date;
import java.util.Scanner;
import java.io.*;

public class Seccion_02 {

	static Scanner keyBoard = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("¿Que ejercicio quieres ejecutar? (x_x)");
		String ejNum = keyBoard.nextLine();

		switch(ejNum) {
			case "2_1":
				ejercicio_2_1();
				break;
			case "2_2":
				ejercicio_2_2();
				break;
			case "2_3":
				ejercicio_2_3();
				break;
			case "2_4":
				ejercicio_2_4();
				break;
			case "2_5":
				ejercicio_2_5();
				break;
			case "2_6":
				ejercicio_2_6();
				break;
			case "2_7":
				ejercicio_2_7();
				break;
			case "2_8":
				ejercicio_2_8();
				break;
		}
		
		keyBoard.close();
	}
	
	public static void ejercicio_2_1() {
		try {
			FileReader fichero = new FileReader("textC.txt");
			int linea=fichero.read();
			while(linea!= -1) {
				System.out.print((char)linea);
				linea=fichero.read();
			}
			fichero.close();
		} catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	public static void ejercicio_2_2() {
		System.out.println("¿A que velocidad quieres que se reproduzca? (Una velocidad de 1000=1segundo recomendamos de 10 a 20)");
		int speed = Integer.parseInt(keyBoard.nextLine());
		try {
			FileReader fichero = new FileReader("textC.txt");
			int linea=fichero.read();
			while(linea!= -1) {
				System.out.print((char)linea);
				linea=fichero.read();
				Thread.sleep(speed);
			}
			fichero.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void ejercicio_2_3() {
		System.out.println("¿A que velocidad quieres que se reproduzca? (Una velocidad de 1000=1segundo recomendamos de 10 a 20)");
		
		int speed = Integer.parseInt(keyBoard.nextLine());
		String next;
		
		try {
			FileReader fichero = new FileReader("textC.txt");
			int linea=fichero.read();
			while(linea!= -1) {
				for(int i=linea; i<(linea+100); i++) {
					if(linea == -1) break;
					System.out.print((char)linea);
					linea=fichero.read();
					Thread.sleep(speed);
				}
				if(linea != -1) {
					System.out.println("\n\nPulse enter para continuar...");
					next = keyBoard.nextLine(); //Prueba para seguir pulsando enter
					//System.in.read(); //NO FUNCIONA COMO QUEREMOS - Prueba para seguir pulsando cualquier tecla
				}
				
			}
			fichero.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void ejercicio_2_4() {
		try {
			FileReader fichero = new FileReader("textC.txt");
			BufferedReader bR = new BufferedReader(fichero);
			String linea=bR.readLine();
			while(linea != null) {
				System.out.print(linea);
				linea=bR.readLine();
			}
			fichero.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void ejercicio_2_5() {
		try {
			FileReader fichero = new FileReader("textC.txt");
			BufferedReader bR = new BufferedReader(fichero);
			String linea=bR.readLine();
			while(linea != null) {
				System.out.println(linea);
				linea=bR.readLine();
				Thread.sleep(1000);
			}
			bR.close();
			fichero.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void ejercicio_2_6() {
		try {
			FileWriter fW = new FileWriter("textC_2.txt");
			BufferedWriter bW = new BufferedWriter(fW);
			FileReader fR = new FileReader("textC.txt");
			BufferedReader bR = new BufferedReader(fR);
			String linea=bR.readLine();
			
			while(linea != null) {
				bW.write(linea);
				bW.newLine();
				linea=bR.readLine();
			}
			
			bR.close();
			fR.close();
			bW.close();
			fW.close();
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
		System.out.println("Se ha copiado el texto de un archivo a otro correctamente.");
	}
	
	public static void ejercicio_2_7() {
		try {
			FileWriter fW = new FileWriter("textC_3.txt");
			BufferedWriter bW = new BufferedWriter(fW);
			String linea = "";
			while(!linea.equals("exit")) {
				System.out.println("Indicame el texto que quieras añadir al documento, para finalizar el programa utiliza la palabra exit");
				linea = keyBoard.nextLine();
				if(!linea.equals("exit")) {
					bW.write(linea);
					bW.newLine();
				}
			}
			bW.close();
			fW.close();
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
		System.out.println("Programa finalizado.");
	}
	
	public static void ejercicio_2_8() {
		File fichero = new File("textC_3.txt");
		
		try {
			FileWriter fW = new FileWriter("textC_4.txt");
			BufferedWriter bW = new BufferedWriter(fW);
			String linea = "";
			while(!linea.equals("exit")) {
				System.out.println("Indicame el texto que quieras añadir al documento, para finalizar el programa utiliza la palabra exit");
				linea = keyBoard.nextLine();
				if(!linea.equals("exit")) {
					bW.write(linea);
					bW.newLine();
				}
			}
			Date fecha = new Date(fichero.lastModified());
			@SuppressWarnings("deprecation")
			String newNameF = fichero.getName()+"_"+(fecha.getYear()+1900)+""+fecha.getMonth()+""+fecha.getDay()+"_"+fecha.getHours()+"-"+fecha.getMinutes();
			fichero.renameTo(new File(newNameF));
			bW.close();
			fW.close();
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
		System.out.println("Programa finalizado.");
	}

}
