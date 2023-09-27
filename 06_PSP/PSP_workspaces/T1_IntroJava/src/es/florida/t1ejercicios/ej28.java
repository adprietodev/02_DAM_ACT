package es.florida.t1ejercicios;

import java.util.Scanner;

public class ej28 {

	public static void main(String[] args) {
		
		Scanner keyBoard = new Scanner(System.in);
		
		System.out.print("Indicame el tipo, marca y modelo del vehiculo que quieres registrar, 5 vehiculos en total.");
		
		Vehiculo[] arrVehiculos = new Vehiculo[5];
		
		String tipo,marca,modelo;
		
		for(int i=0; i < 5; i++) {
			System.out.println("Vehiculo "+(i+1));
			System.out.print("Tipo: ");
			tipo = keyBoard.nextLine();
			System.out.print("Marca: ");
			marca = keyBoard.nextLine();
			System.out.print("Modelo: ");
			modelo = keyBoard.nextLine();
			
			Vehiculo vehiculo = new Vehiculo(tipo,marca,modelo);
			
			arrVehiculos[i] = vehiculo;
		}
		
		System.out.println("Información sobre los vehiculos");
		for(Vehiculo vehiculo : arrVehiculos) {
			System.out.println(vehiculo.mostrarInfo());
		}
		
		keyBoard.close();

	}

}
