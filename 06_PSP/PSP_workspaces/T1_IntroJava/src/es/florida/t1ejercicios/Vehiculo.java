package es.florida.t1ejercicios;

public class Vehiculo {
	
	protected String tipo;
	protected String marca;
	protected String modelo;
	
	public Vehiculo() {};
	
	public Vehiculo( String tipo, String marca, String modelo) {
		this.tipo = tipo;
		this.marca = marca;
		this.modelo = modelo;
	}
	
	public String mostrarInfo() {
		return "El tipo del vehiculo es "+this.tipo+" de la marca "+this.marca+" y modelo "+this.modelo;
	}

}
