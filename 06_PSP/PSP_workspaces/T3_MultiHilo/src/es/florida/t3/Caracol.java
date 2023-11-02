package es.florida.t3;

public class Caracol implements Runnable{

	private Double velocidad;
	private String nombre;
	private Double distancia;
	
	public Caracol(Double velocidad, String nombre) {
		this.velocidad = velocidad;
		this.nombre = nombre;
		this.distancia = 0.0;
	}
	
	@Override
	public void run(){
		
		System.out.println(nombre+" ha comenzado la carrera");
		while(distancia < 1) {
			
			avanzar();
			mostrarPorcentaje();
		}
		
		System.out.println(nombre+" terminado la carrera");
		
	}
	
	public void avanzar(){
		Double distanciaAvanzada = velocidad * Math.random()*0.1;
		distancia += distanciaAvanzada;
	}
	
	public void mostrarPorcentaje() {
		if(distancia >= 1) distancia = 1.0;
		System.out.println(nombre+" lleva un "+String.format("%.2f", distancia*100)+"% de la carrera");
	}
}
