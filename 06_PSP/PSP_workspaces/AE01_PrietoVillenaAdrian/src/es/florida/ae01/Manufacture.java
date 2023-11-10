package es.florida.ae01;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

public class Manufacture {

	private List<Piece> pieces; //Lista de piezas
	private List<Piece> quequePieces; //Lista de cola
	private List<String> finishPieces; //Lista de piezas finalizadas
	private Map<String, Integer> timeManufacture;// dependiendo el tipo que sea cogeremos le asignaremos un tiempo al crear la pieza
	private int countMFPiece; //Cuenta de piezas fabricadas
	private int maxMachines; //Maximo de maquinas
	private int totalQuantityPieces; //Cantidad total de piezas
	private int[][] saveIndexQ; //Indices guardados de la cola para manipular la cola con las maquinas
	
	Thread[] threadsMachines;

	//Consturctor de manufacture
	public Manufacture() {

		pieces = new ArrayList<Piece>();
		quequePieces = new ArrayList<Piece>();
		timeManufacture = new HashMap<>();
		finishPieces = new ArrayList<String>();

		
		countMFPiece = 0;
		maxMachines = 8;
		totalQuantityPieces = 0;
		
		threadsMachines = new Thread[8]; //Para que haya 8 maquinas 
		saveIndexQ = new int[8][2];


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
		Order order = new Order();

		/*
		 * Separamos los datos y guardamos cada dato de la pieza (Tipo cantidad) 
		 * ej: I 10 en una array para poder generar las piezas mas adelante
		 */
		String[] firstSplit = args[0].split(";");
		String numProcess = args[1];

		//Recorremos la array firstSplit para crear las piezas.
		for (int i = 0; i < firstSplit.length; i++) {
			String[] secondS = firstSplit[i].split(" "); //Volvemos a separar por espacio para asignar el tipo y la cantidad a la lista de Piezas
			mF.pieces.add(
					new Piece(secondS[0], Integer.parseInt(secondS[1]), mF.timeManufacture.get(secondS[0]), mF,0)); //Creamos la pieza y la añadimos.
			mF.totalQuantityPieces += Integer.parseInt(secondS[1]); //Sumamos la cantidad de la pieza a una variable global para saber la cantidad total de piezas.
		}

		//Indicamos que si el total de piezas es menor que el maximo de maquinas se rebaje la cantidad de maquinas, ya que vamos a utilizar menos menos maquinas
		if (mF.totalQuantityPieces < mF.maxMachines) {
			mF.maxMachines = mF.totalQuantityPieces; 
			mF.threadsMachines = new Thread[mF.maxMachines];
		}
			

		
		int totalQueque = 0; //Iniciamos una como contador de total de la lista para salir del siguiente while.
		
		//While donde creamos la cola.
		while (totalQueque != mF.totalQuantityPieces) {
			for (Piece piece : mF.pieces) {
				if (piece.quantity != 0) { // Si la cantidad de la pieza es 0 no la añadimos a la cola.
					mF.quequePieces.add(piece);
					totalQueque += piece.lessQuantity(); //Restamos una pieza despues de añadirla a la cola.
				}
			}

		}
		
		//While donde comenzaremos a fabricar las piezas.
		while (mF.countMFPiece != mF.totalQuantityPieces) { //Repetir hasta que la cantidad de piezas creadas sea igual que el total de piezas.
			for (int i = 0; i < mF.maxMachines; i++) { //for donde recorremos la cantidad de maquinas (el maximo sera 8 y puede disminuir)
				if (mF.threadsMachines[i] == null) { //Comprobamos si el thread es null para añadir una pieza de la cola al proceso
					mF.quequePieces.get(mF.countMFPiece).setIndex(i); //pasamos el indice que tiene en la array de la maquina.
					mF.threadsMachines[i] = new Thread(mF.quequePieces.get(mF.countMFPiece)); //asignamos un nuevo thread a la posición de la array para ejecutarlo posteriormente
					mF.threadsMachines[i].start(); //ejecutamos el thread
					mF.saveIndexQ[i][0] = mF.countMFPiece; //Guardamos el indice de la cola para identificar la pieza mas adelante
					mF.saveIndexQ[i][1] = 0; //0 igual a false en nuestra logica
					mF.countMFPiece++; //sumamos la cuenta de pieza fabricada o mejor dicho fabricandose.
					
				}
				if(mF.saveIndexQ[i][1] == 1) { //Si la pieza se ha fabricado liberara una maquina para añadir la siguiente pieza
					
					mF.freeMachine(i); //Llamamos al metodo para liberar la maquina.
					
				}
				
			}
			
		}
		
		//Comprobamos si han finalizado todos los hilos.
		for (Thread thread : mF.threadsMachines) {
			try {
				thread.join(); // Esperar a que el hilo termine
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}

		//Llamamos al metodo para escribir el archivo de LOG.
		mF.writeLogFile();
		
		//Le pasamos un mensaje a order para que lo muestre por interfaz indicando que el proceso acabado.
		order.messDialog("Proceso "+numProcess+": Ha finalizado la fabricación de todas las piezas", "Fin fabricación", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Metodo que utilizamos para generar el archivo de LOG con los datos guardados en finishPieces.
	 */
	public void writeLogFile() {
		Date fechaHoraActual = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat h = new SimpleDateFormat("HHmmss");
		String fechaFor = f.format(fechaHoraActual);
		String horaFor = h.format(fechaHoraActual);
		String nameFile = "LOG_" + fechaFor + "_" + horaFor + ".txt";
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

	/** 
	 * Metodo que llamamos para liberar una maquina por que ha finalizado la fabricación de una anterior.
	 * @param posMachine pasamos la maquina que ha finalizado para pasarla a null, el numero de la maquina es el del indice de la array.
	 */
	public void freeMachine(int posMachine) {
		threadsMachines[posMachine] = null;
	}
	
	/**
	 * Metodo que utilizamos para guardar los datos de la pieza cuando haya finalizado, utilizamos la sincronización para evitar fallos de guardado
	 * @param data información de la pieza "Tipo_Fecha_hora" EJ: "I_20231021_153020"
	 */
	private synchronized void saveList(String data) {
		finishPieces.add(data);
	}

	/**
	 * Metodo que utilizamos para fabricar la pieza
	 * @param tiempoFabricacion le pasamos el tiempo que tarda la pieza en generarse.
	 */
	public void procesoFabricacion(int tiempoFabricacion) {
		long tiempoInicio = System.currentTimeMillis();
		long tiempoFin = tiempoInicio + tiempoFabricacion; // Tiempo de fabricacion en milisegundos
		int iteraciones = 0;
		while (System.currentTimeMillis() < tiempoFin) {
			// Realizar iteraciones para consumir procesador (simula ocupacion de maquina)
			iteraciones++;
		}
		
		
	}

	
	/**
	 * Clase que utilizamos para crear la pieza.
	 */
	public static class Piece implements Runnable {

		private String type; //Tipo o nombre de la pieza
		private int quantity; //Cantidad de piezas que hay que hacer
		private int timeManufacture; //Tiempo que tarda cada pieza en realizarse.
		private Manufacture mF; 
		private int index; //Indice que utilizaremos mas adelante para saber en que maquina esta, por defecto le pasaremos 0 al inicio.

		//Constructor de Piece.
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

		

		/**
		 * Metodo donde llamamos para restar una  pieza.
		 * @return retornamos 1 para sumar y saber si coincide con el total de piezas.
		 */
		public int lessQuantity() {
			quantity--;
			return 1;
		}

		/**
		 * Cogemos el indice que tiene de la maquina
		 * @return devolvemos ese indice
		 */
		public int getIndex() {
			return index;
		}
		
		/**
		 * Metodo donde asignamos un indice de la maquina que corresponde
		 * @param i posición de la maquina donde se esta ejecutando.
		 */
		public void setIndex(int i) {
			this.index = i;
		}

	}

}
