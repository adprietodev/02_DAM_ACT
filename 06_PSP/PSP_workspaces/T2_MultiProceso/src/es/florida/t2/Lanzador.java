package es.florida.t2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Lanzador {
	
	public static void lanzarSumador(Integer n1, Integer n2) {
		
		try {
			String clase = "es.florida.t2.Sumador";
			String javaHome = System.getProperty("java.home");
			String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
			String classpath = System.getProperty("java.class.path");
			String className = clase;
			
			List<String> command = new ArrayList<>();
			command.add(javaBin);
			command.add("-cp");
			command.add(classpath);
			command.add(className);
			command.add(n1.toString());
			command.add(n2.toString());
			
			System.out.println("Comando que se pasa a ProcessBuilder: "+command);
			System.out.println("Comando a ejecutar en cmd.exe: "+command.toString().replace(",", ""));
			
			ProcessBuilder builder = new ProcessBuilder(command);
			//builder.directory(new File("DirSum"));
			Process process = builder.inheritIO().start();
			
			process.waitFor();
			System.out.println(process.exitValue());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		Lanzador launcher = new Lanzador();
		Sumador sumador = new Sumador();
		sumador.clearFile();
		lanzarSumador(1,50);
		lanzarSumador(51,100);
		System.out.println("OK");

	}

}
