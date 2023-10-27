package es.florida.t2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Task implements Runnable {
	
	private DataNeo data;
	
	public Task(DataNeo data) {
		this.data = data;
	}
	
	
	
	public void run() {
		
		ProbabilityCollisionNEO pCN = new ProbabilityCollisionNEO();
		LanzadorNeo lN = new LanzadorNeo();
		
		data.setStatus(false);
		
		double result = pCN.calculateProbability(data.getPosNeo(), data.getSpeedNeo());
		data.setResult(result);
		String strResult = String.format("%.2f", result)+"%";
		String name = data.getName();
		
		File file = new File("neoResult"+File.separator+name+".txt");
		try {
			FileWriter fW = new FileWriter(file);
			BufferedWriter bW = new BufferedWriter(fW);
			bW.write("La probabilidad es: "+strResult);
			bW.newLine();
			
			bW.close();
			fW.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(result > 10) {
			data.setMess("ID: "+data.getId()+" - ALERTA MUNDIAL el NEO "+name+" tiene una probabilidad de "+strResult );
		}else {
			data.setMess("ID: "+data.getId()+" - Con el NEO "+name+" podemos estar tranquilos, solo tiene una probabilidad de "+strResult);
		}
		
		data.setStatus(true);
		
	}

}
