package es.florida.t2;

import java.io.File;

public class LanzadorProcesos {

	public void ejecutar(String ruta) {
		ProcessBuilder pB;
		try {
			pB = new ProcessBuilder(ruta);
			pB.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		File calc = new File("/system/Applications/Calculator.app");
		System.out.println(calc.exists());
		String ruta = "/system/Applications/Calculator.app";
		LanzadorProcesos lP = new LanzadorProcesos();
		lP.ejecutar(ruta);
		System.out.println("Finalizado");

	}

}
