package es.florida.t2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	
	public List<DataNeo> saveData() {
		LanzadorNeo lN = new LanzadorNeo();
		File neos = new File("neos.txt");
		ProbabilityCollisionNEO pCN = new ProbabilityCollisionNEO();
		String[][] datos = new String[lN.countLn()][];
		int count=0;
		
		try {
			
			FileReader fR = new FileReader(neos);
			BufferedReader bR = new BufferedReader(fR);
			String linea = bR.readLine();
			
			for(int i= 0; i < lN.countLn(); i++) {
				datos[i] = linea.split(",");
				
				lN.dataList.add(new DataNeo(datos[i][0],Double.parseDouble(datos[i][1]),Double.parseDouble(datos[i][2]), false,"",(i+1),0.0));
				linea = bR.readLine();
				count++;
				
			}
			
			bR.close();
			fR.close();
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return lN.dataList;
	}
	
	/*
	 * public void generateFile(String name , double result) {
	 * 
	 * String strResult = String.format("%.2f", result)+"%";
	 * 
	 * File file = new File("neoResult"+File.separator+name+".txt"); try {
	 * FileWriter fW = new FileWriter(file); BufferedWriter bW = new
	 * BufferedWriter(fW); bW.write("La probabilidad es: "+strResult); bW.newLine();
	 * 
	 * bW.close(); fW.close();
	 * 
	 * } catch(Exception e) { e.printStackTrace(); }
	 * 
	 * if(result > 10) { System.err.println("ALERTA MUNDIAL el NEO "
	 * +name+" tiene una probabilidad de "+strResult ); }else {
	 * System.out.println("Con el NEO "
	 * +name+" podemos estar tranquilos, solo tiene una probabilidad de "+strResult
	 * ); }
	 * 
	 * }
	 */
	
	public boolean stateCheck(Boolean state) {
		return state;
	}
	
	public static void main(String[] args) {
		
		long tiempoInicio = System.currentTimeMillis();
		
		LanzadorNeo lN = new LanzadorNeo();
		ProbabilityCollisionNEO pCN = new ProbabilityCollisionNEO();
		int posList = 0;
		int aP = Runtime.getRuntime().availableProcessors();
		int countReady = 0;
        lN.dataList = lN.saveData();
        
        Task taskC;
        
        while(posList != lN.dataList.size()) {
        		for(int i = 0; i < aP || posList < lN.dataList.size(); i++) {
        			taskC = new Task(lN.dataList.get(i));
        			Thread hilo=new Thread(taskC);
        			hilo.start();
        			posList++;
        		}
        }
        
        while(countReady != lN.dataList.size()) {
        		for(int i = 0; i < lN.dataList.size(); i++) {
        			if(lN.dataList.get(i).getState()) {
        				if(lN.dataList.get(i).getResult() > 10) {
        					System.err.println(lN.dataList.get(i).getMess());
        				}else {
        					System.out.println(lN.dataList.get(i).getMess());
        				}
        				lN.dataList.remove(lN.dataList.get(i));
        			}
        		}
        }
        
        
		//ExecutorService executor = Executors.newFixedThreadPool(availableProcessors);
        //for(DataNeo item : lN.dataList) {
        	//	executor.execute(new Task(item.getName(),item.getPosNeo(),item.getSpeedNeo()));
        //}
        //executor.shutdown();
        
        long tiempoFin = System.currentTimeMillis();
        long tiempoTotal = tiempoFin - tiempoInicio;

        System.out.println("Tiempo de ejecución de los calculos es: " + tiempoTotal + " milisegundos");
		
		
	}

}
