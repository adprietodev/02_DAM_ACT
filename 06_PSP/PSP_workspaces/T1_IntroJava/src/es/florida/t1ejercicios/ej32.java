package es.florida.t1ejercicios;

import java.util.ArrayList;
import java.util.List;

public class ej32 {

	public static void main(String[] args) {
		
		String[] alumnos = {"Adrián","Alejandro","Sandra","Sergio","Anna","Enma"};
		List<String> listAlumnos = new ArrayList<>();
		System.out.println("Sacado por array: ");
		for(int i=0; i<alumnos.length;i++) {
			
			System.out.println(alumnos[i]);
			listAlumnos.add(alumnos[i]);
		}
		
		System.out.println("Sacado por lista: ");
		for(String name : listAlumnos) {	
			System.out.println(name);
		}
		
		
	}

}
