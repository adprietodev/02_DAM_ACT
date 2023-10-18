package es.florida.t2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Sumador {

	public int sumar(int n1, int n2) {
		System.out.println("Sumando de "+n1+" hasta "+n2);
		int resultado = 0;
		for(int i = n1; i <= n2; i++) {
			resultado += i;
		}
		return resultado;
	}
	
	public void writeResult(String result) {
		File fileResult = new File("resultado.txt");
		//fileResult.delete();
		try {
			FileWriter fW = new FileWriter(fileResult, true);
			BufferedWriter bW = new BufferedWriter(fW);
			
			bW.write("El resultado es: "+result);
			bW.newLine();
			
			bW.close();
			fW.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public void clearFile() {
		File fileResult = new File("resultado.txt");
		
		if(fileResult.exists()) {
			try {
				FileWriter fW = new FileWriter(fileResult, false);
				BufferedWriter bW = new BufferedWriter(fW);
				
				bW.write("");
				
				bW.close();
				fW.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		Sumador sum = new Sumador();
		int n1 = Integer.parseInt(args[0]);
		int n2 = Integer.parseInt(args[1]);
		int resultado = sum.sumar(n1, n2);
		sum.writeResult(Integer.toString(resultado));
		System.out.println("Resultado de este sumador: "+resultado);

	}

}
