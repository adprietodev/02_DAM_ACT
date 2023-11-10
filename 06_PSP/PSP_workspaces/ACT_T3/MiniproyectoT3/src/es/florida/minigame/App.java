package es.florida.minigame;

public class App {

	public Mina mina= new Mina(100);
	
	public static void main(String[] args) {
		
		App app = new App();
		Thread[] hilos = new Thread[14];
		Minero[] mineros = new Minero[14];
		
		for(int i=0; i < mineros.length; i++) {
			mineros[i] = new Minero(app.mina,0,1000);
			hilos[i] = new Thread(mineros[i]);
			hilos[i].start();
		}
		
		for(Thread hilo : hilos) {
			try {
				hilo.join();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		int totalRecolectado = 0;
		
		
		for(int i=0; i < mineros.length; i++) {
			System.out.println("El minero "+(i+1)+" tiene en la bolsa: "+mineros[i].getBolsa());
			totalRecolectado += mineros[i].getBolsa();
		}

		System.out.println("El total recolectado por los mineros es: "+totalRecolectado);
		System.out.println("El stock actual es: "+ app.mina.getStock());
	}

}
