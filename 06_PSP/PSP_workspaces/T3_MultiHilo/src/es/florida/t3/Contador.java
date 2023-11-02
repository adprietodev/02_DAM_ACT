package es.florida.t3;

public class Contador implements Runnable {

	public String nombreHilo;
	public int inicioContador;
	public int limiteContador;
	
	public Contador(String nombreHilo, int inicioContador, int limiteContador) {
		this.nombreHilo = nombreHilo;
		this.inicioContador = inicioContador;
		this.limiteContador = limiteContador;
	}

	@Override
	public void run() {
		
		for(int i = inicioContador; i < limiteContador; i++) {
			System.out.println(nombreHilo+" "+i);
		}
		
	}
	
}
