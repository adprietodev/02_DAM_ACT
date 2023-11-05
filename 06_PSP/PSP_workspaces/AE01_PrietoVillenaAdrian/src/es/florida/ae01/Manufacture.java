package es.florida.ae01;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Manufacture {

	private List<Piece> pieces;
	private List<Piece> quequePieces;
	private List<String> finishPieces;
	private Map<String, Integer> timeManufacture;
	private int countMFPiece;
	private int maxMachines;
	private int totalQuantityPieces;
	private int[][] saveIndexQ;
	
	Thread[] threadsMachines;

	public Manufacture() {

		pieces = new ArrayList<Piece>();
		quequePieces = new ArrayList<Piece>();
		timeManufacture = new HashMap<>();
		finishPieces = new ArrayList<String>();

		threadsMachines = new Thread[8]; //Para que haya 8 maquinas 
		saveIndexQ = new int[8][2];

		countMFPiece = 0;
		maxMachines = 8;
		totalQuantityPieces = 0;

		timeManufacture.put("I", 1000);
		timeManufacture.put("O", 2000);
		timeManufacture.put("T", 3000);
		timeManufacture.put("J", 4000);
		timeManufacture.put("L", 4000);
		timeManufacture.put("S", 5000);
		timeManufacture.put("Z", 5000);
	}

	public static void main(String[] args) {

		Manufacture mF = new Manufacture();

		String[] firstSplit = args[0].split(";");

		for (int i = 0; i < firstSplit.length; i++) {
			String[] secondS = firstSplit[i].split(" ");
			mF.pieces.add(
					new Piece(secondS[0], Integer.parseInt(secondS[1]), mF.timeManufacture.get(secondS[0]), mF,0));
			mF.totalQuantityPieces += Integer.parseInt(secondS[1]);
		}

		if (mF.totalQuantityPieces < mF.maxMachines)
			mF.maxMachines = mF.totalQuantityPieces;

		int totalQueque = 0;
		while (totalQueque != mF.totalQuantityPieces) {
			for (Piece piece : mF.pieces) {
				if (piece.quantity != 0) {
					mF.quequePieces.add(piece);
					totalQueque += piece.lessQuantity();
				}
			}

		}
		
		//System.out.println(mF.totalQuantityPieces);
		while (mF.countMFPiece != mF.totalQuantityPieces) {
			//System.out.println(mF.countMFPiece);
			for (int i = 0; i < mF.threadsMachines.length; i++) {
				if (mF.threadsMachines[i] == null) {
					//System.out.println(mF.countMFPiece);
					mF.quequePieces.get(mF.countMFPiece).setIndex(i);
					mF.threadsMachines[i] = new Thread(mF.quequePieces.get(mF.countMFPiece));
					mF.threadsMachines[i].start();
					mF.saveIndexQ[i][0] = mF.countMFPiece;
					mF.saveIndexQ[i][1] = 0; //0 igual a false en nuestra logica
					mF.countMFPiece++;
					
				}
				if(mF.saveIndexQ[i][1] == 1) {
					
					mF.freeMachine(i);
					
				}
				
			}
			
		}
		
		for (Thread thread : mF.threadsMachines) {
			try {
				thread.join(); // Esperar a que el hilo termine
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}

		mF.writeLogFile();
	}

	public void writeLogFile() {
		Date fechaHoraActual = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat h = new SimpleDateFormat("HHmmss");
		String fechaFor = f.format(fechaHoraActual);
		String horaFor = h.format(fechaHoraActual);
		String nameFile = "LOG_" + fechaFor + "_" + horaFor + ".txt";
		// "LOG_"+fechaFor+"_"+horaFor+".txt"
		try {

			FileWriter fW = new FileWriter(nameFile);
			BufferedWriter bW = new BufferedWriter(fW);

			for (String data : finishPieces) {

				bW.write(data);
				bW.newLine();

			}

			bW.close();
			fW.close();
		} catch (IOException e) {
			System.out.println(e);

		}
	}

	public void freeMachine(int posMachine) {
		threadsMachines[posMachine] = null;
	}
	
	private synchronized void saveList(String data) {
		finishPieces.add(data);
	}

	public void procesoFabricacion(int tiempoFabricacion) {
		long tiempoInicio = System.currentTimeMillis();
		long tiempoFin = tiempoInicio + tiempoFabricacion; // Tiempo de fabricacion en milisegundos
		int iteraciones = 0;
		while (System.currentTimeMillis() < tiempoFin) {
			// Realizar iteraciones para consumir procesador (simula ocupacion de maquina)
			iteraciones++;
		}
		
		
	}

	public static class Piece implements Runnable {

		private String type;
		private int quantity;
		private int timeManufacture;
		private Manufacture mF;
		private int index;

		public Piece(String type, int quantity, int timeManufacture, Manufacture mF, int index) {
			this.type = type;
			this.quantity = quantity;
			this.timeManufacture = timeManufacture;
			this.mF = mF;
			this.index = index;
		}

		@Override
		public void run() {
			mF.procesoFabricacion(timeManufacture);
			Date fechaHoraActual = new Date();
			SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat h = new SimpleDateFormat("HHmmss");
			String fechaFor = f.format(fechaHoraActual);
			String horaFor = h.format(fechaHoraActual);
			mF.saveList(type + "_" + fechaFor + "_" + horaFor);//Hay un synchronized en esta función para guardar los datos;
			System.out.println(type + "_" + fechaFor + "_" + horaFor);
			mF.saveIndexQ[index][1] = 1;
		}

		

		public int lessQuantity() {
			quantity--;
			return 1;
		}

		public int getIndex() {
			return index;
		}
		public void setIndex(int i) {
			this.index = i;
		}

	}

}
