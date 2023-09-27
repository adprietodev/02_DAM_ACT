package es.florida.t1ejercicios;

import java.util.ArrayList;
import java.util.Scanner;

public class ej29 {

	public static void main(String[] args) {
		
		
		
		Scanner keyBoard = new Scanner(System.in);
		
		System.out.print("Indicame el tipo, marca y modelo del vehiculo que quieres registrar, 5 vehiculos en total.");
		
		ArrayList<Vehiculo> arrVehiculos = new ArrayList<Vehiculo>();
		
		String tipo,marca,modelo,conf = "s";
		int i = 1;
		
		while(!conf.equals("n")) {
			System.out.println("Vehiculo "+i);
			System.out.print("Tipo: ");
			tipo = keyBoard.nextLine();
			System.out.print("Marca: ");
			marca = keyBoard.nextLine();
			System.out.print("Modelo: ");
			modelo = keyBoard.nextLine();
			
			Vehiculo vehiculo = new Vehiculo(tipo,marca,modelo);
			
			arrVehiculos.add(vehiculo);
			
			System.out.println("¿Quieres añadir otro vehiculo SI/NO (s/n) ?");
			conf = keyBoard.nextLine();
		}
		
		System.out.println("Información sobre los vehiculos");
		for(Vehiculo vehiculo : arrVehiculos) {
			System.out.println(vehiculo.mostrarInfo());
		}
		
		keyBoard.close();	

	}

}
