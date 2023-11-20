package es.florida.examen_psp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lanzador {

	private int numKB = 0;
	private boolean stop = false;

	public static void main(String[] args) {
		Lanzador l = new Lanzador();

		l.startLanzador();

	}

	public void startLanzador() {

		Scanner keyBoard = new Scanner(System.in);
		System.out.println("Indicame el numero de piezas que quieres fabricar(Debes ser  multiplo de 3): ");

		boolean checkNum = false;

		while (!checkNum) {

			try {
				numKB = Integer.parseInt(keyBoard.nextLine());
				if ((numKB % 3) == 0) {
					checkNum = true;
				} else {
					System.err.println(
							"El numero que has facilitado no es multiplo de 3, por favor introduce un numero válido.");
				}

			} catch (NumberFormatException e) {
				System.err.println("Caracter insertado no valido, por favor introduce un numero");
			}
		}

		callSubproceso();
	}

	public void callSubproceso() {
		try {

			String clase = "es.florida.examen_psp.Subproceso";
			String javaHome = System.getProperty("java.home");
			String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
			String classpath = System.getProperty("java.class.path");
			String className = clase;

			List<String> command = new ArrayList<>();
			command.add(javaBin);
			command.add("-cp");
			command.add(classpath);
			command.add(className);
			command.add(String.valueOf(numKB));

			ProcessBuilder builder = new ProcessBuilder(command);
			builder.inheritIO();

			Process process = builder.start();

			process.waitFor();

			while (!stop) {
				Scanner keyBoard = new Scanner(System.in);
				System.out.println("¿Quieres lanzar otra fabricación?(y/n): ");
				String res = keyBoard.nextLine();
				if (res.toLowerCase().equals("y")) {
					startLanzador();
					stop = true;
				} else if (res.toLowerCase().equals("n")) {
					System.out.println("Gracias por usar nuestra fabricación de piezas. ¡Hasta la próxima!");
					stop = true;
				} else {
					System.err.println(
							"No has insertado un caracter valido, recuerda que debe ser y en caso de si y n en caso de no");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
