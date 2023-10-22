package es.florida.t2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Lanzador {
	
	public int countLine = 0;
	public Boolean itsReady = false;
	
	public static void lanzarSumador(Integer n1, Integer n2, String nameFile) {
		Lanzador laun = new Lanzador();
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
			
			//System.out.println("Comando que se pasa a ProcessBuilder: "+command);
			//System.out.println("Comando a ejecutar en cmd.exe: "+command.toString().replace(",", ""));
			
			ProcessBuilder builder = new ProcessBuilder(command);
			builder.inheritIO();
			builder.redirectOutput(new File(nameFile));
			Process process = builder.start();
			
			//process.waitFor();
			//System.out.println(process.exitValue());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int checkLnFile() {
		File fileRes = new File("resultado.txt");
		int countLn = 0;
		try {
			
				FileReader fR = new FileReader(fileRes);
				BufferedReader bR = new BufferedReader(fR);
				String linea = bR.readLine();
				
				
				while(linea != null) {
					countLn++;
					linea = bR.readLine();
				}
				
				bR.close();
				fR.close();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return countLn;
	}
	
	public void readFile() {
		File fileRes = new File("resultado.txt");
		try {
			
				FileReader fR = new FileReader(fileRes);
				BufferedReader bR = new BufferedReader(fR);
				String linea = bR.readLine();
				
				
				while(linea != null) {
					System.out.println(linea);
					linea = bR.readLine();
				}
				
				bR.close();
				fR.close();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		if(new File("resultado.txt").exists()) {
			new File("resultado.txt").delete();
		}
		if(new File("out-01.txt").exists()) {
			new File("out-01.txt").delete();
		}
		if(new File("out-02.txt").exists()) {
			new File("out-02.txt").delete();
		}
		
		Lanzador launcher = new Lanzador();
		Sumador sumador = new Sumador();
		//sumador.clearFile();
		lanzarSumador(1,50, "out-01.txt");
		lanzarSumador(51,100, "out-02.txt");
		
		while(!new File("resultado.txt").exists()) {
			
		}
		while(launcher.checkLnFile() != 2) {
			
		}
		
		launcher.readFile();

	}

}
