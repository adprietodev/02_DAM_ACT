package es.florida.t3;

import java.io.*;

public class RacesSnailsFile implements Runnable {

	private Double velocidad;
	private String nombre;
	private Double distancia;
	private boolean finish;

	public RacesSnailsFile(Double velocidad, String nombre) {
		this.velocidad = velocidad;
		this.nombre = nombre;
		this.distancia = 0.0;
		this.finish = false;
	}

	@Override
	public void run() {
		
		//System.out.println(nombre+" ha comenzado la carrera");
		while(!finish) {
			
			avanzar();
			mostrarPorcentaje();
			
			writeWinner(nombre);
		}
		
		if(distancia < 1) {
			System.out.println(nombre+" ha abandonado la carrera en el "+String.format("%.2f", distancia).replace(",", ".")+"% de la carrera");
		}

	}

	public static void main(String[] args) {
		
		File winners = new File("winner.txt");
		if(winners.exists()) {
			clearFileWinners(winners);
		}
		
		String[] names = { "Borja", "Adrián", "Raquel", "German", "Toni", "Aitor" };
		Thread[] snails = new Thread[names.length];
		for (int i = 0; i < names.length; i++) {
			snails[i] = new Thread(new RacesSnailsFile(speedRandom(), names[i]));
			snails[i].start();
		}
		
		for(Thread snail : snails) {
			try {
				snail.join();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(readNameWinner()+" ha ganado la carrera con el 100%");

	}
	
	public boolean checkWinner()  {
		//System.out.println(nombre+" revisa si hay ganador");
		boolean areWinner = false;
		File w = new File("winner.txt");
		if(w.exists()) {
			try {
				FileReader fR = new FileReader(new File("winner.txt"));
				BufferedReader bR = new BufferedReader(fR);
				if(bR.readLine() == null){
					areWinner = false;
				} else {
					areWinner = true;
				}
				bR.close();
				fR.close();
			} catch(IOException e) {
				
				e.printStackTrace();
			}
		}
		return areWinner;
		
	}
	
	public static String readNameWinner() {
		String name = "";
		try {
			FileReader fR = new FileReader(new File("winner.txt"));
			BufferedReader bR = new BufferedReader(fR);
			
			name = bR.readLine();
			
			bR.close();
			fR.close();
		} catch(IOException e) {
			
			e.printStackTrace();
		}
		
		return name;
	}
	
	public synchronized void writeWinner(String name) {
		if(checkWinner()) {
			finishRace();
		} else {
			
			if(distancia == 1) {
				try {
					FileWriter fW = new FileWriter(new File("winner.txt"));
					BufferedWriter bW = new BufferedWriter(fW);
					bW.write(name);
					bW.newLine();
					
					bW.close();
					fW.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
				finishRace();
			}
		}
	}
	
	public static void clearFileWinners(File winners) {
		
		winners.delete();
	}
	
	public void finishRace() {
		finish = true;
	}
	
	public void avanzar(){
		Double distanciaAvanzada = velocidad * Math.random()*0.1;
		distancia += distanciaAvanzada;
	}
	
	public void mostrarPorcentaje() {
		if(distancia >= 1) distancia = 1.0;
		//System.out.println(nombre+" lleva un "+String.format("%.2f", distancia*100)+"% de la carrera");
	}
	 

	private static Double speedRandom() {
		Double randomSpeed = Math.random() * 0.8;
		String formattedValue = String.format("%.2f", randomSpeed).replace(",", ".");
		randomSpeed = Double.parseDouble(formattedValue);
		return randomSpeed;
	}
}
