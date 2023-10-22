package es.florida.t2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class LanzadorNeo {

	public List<DataNeo> dataList = new ArrayList<>();
	public Boolean checkSD;
	
	public int countLn() {
		File neos = new File("neos.txt");
		int countLn = 0;
		try {
			
			FileReader fR = new FileReader(neos);
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
	
	public void saveData() {
		LanzadorNeo lN = new LanzadorNeo();
		File neos = new File("neos.txt");
		ProbabilityCollisionNEO pCN = new ProbabilityCollisionNEO();
		String[][] datos = new String[lN.countLn()][];
		
		try {
			
			FileReader fR = new FileReader(neos);
			BufferedReader bR = new BufferedReader(fR);
			String linea = bR.readLine();
			
			for(int i= 0; i < lN.countLn(); i++) {
				datos[i] = linea.split(",");
				
				lN.dataList.add(new DataNeo(datos[i][0],Double.parseDouble(datos[i][1]),Double.parseDouble(datos[i][2])));
				linea = bR.readLine();
			}
			
			bR.close();
			fR.close();
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		for(DataNeo neo : lN.dataList) {
			lN.generateFile(neo.getName(), pCN.calculateProbability(neo.getPosNeo(),neo.getSpeedNeo()));
		}
		
	}
	
	public void generateFile(String name , double result) {
		LanzadorNeo lN = new LanzadorNeo();
		
		File file = new File("neoResult"+File.separator+name+".txt");
		try {
			FileWriter fW = new FileWriter(file);
			BufferedWriter bW = new BufferedWriter(fW);
			bW.write("La probabilidad es: "+result);
			bW.newLine();
			
			bW.close();
			fW.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(result > 10) {
			System.err.println("ALERTA MUNDIAL el NEO "+name+" tiene una probabilidad de "+String.format("%.2f", result)+"%" );
		}else {
			System.out.println("Con el NEO "+name+" podemos estar tranquilos, solo tiene una probabilidad de "+String.format("%.2f", result)+"%" );
		}
		
		
	}
	
	public static void main(String[] args) {
		
		long tiempoInicio = System.currentTimeMillis();
		LanzadorNeo lN = new LanzadorNeo();
		ProbabilityCollisionNEO pCN = new ProbabilityCollisionNEO();
		
		lN.saveData();
		

        long tiempoFin = System.currentTimeMillis();
        long tiempoTotal = tiempoFin - tiempoInicio;

        System.out.println("Tiempo de ejecución: " + tiempoTotal + " milisegundos");
		
		
	}

}
